const app = Vue.createApp({

    data() {
        return {
            tickets: [],
            ticket: true,
            cart:[],
        }
    },

    created() {

        axios.get('/api/products/tickets')
        .then(response => {
            console.log(response.data)
            this.tickets = response.data
        })
        .catch(error => {
            return error.message;

        })
    },
    mounted() {
            if (sessionStorage.getItem('cart')) {
                try {
                    this.cart = JSON.parse(sessionStorage.getItem('cart'));
                } catch (e) {
                    sessionStorage.removeItem('cart');
                }
                console.log(this.cart)
            };
        },
    methods: {
        productId(numero) {
            return `#${numero}`
        },
        flyShow(product) {
            this.ticket = product;
        },


        addToCart(ticket){
        console.log(ticket.productId);
            axios.post("/api/clients/current/addToCart/ticket",`ticketId=${ticket.productId}&clase=FIRST&passengers=${1}`)
            .then(resp=>{
                if(ticket.stock<=0){
                   alert("No stock");}
                   else{
                     ticket.stock--;
                     if(this.cart.some(prod=>prod.productId==ticket.productId)){
                       ticket.quantity++;}
                     else{ticket.quantity=1;
                     this.cart.push(ticket);}
                ticket.subtotal=ticket.quantity*ticket.price;
                console.log(this.cart);
                this.savingCart();}
            })
            .catch(err=> console.log(err));
        },
        deleteCartObject(ticket){
            if(this.cart.some(prod=>prod.productId==ticket.productId)){
                let id=this.cart.findIndex(prod => prod.productId == ticket.productId);
                if(this.cart[id].stock>=1){
                ticket.stock=ticket.stock+this.cart[id].quantity;
                this.cart[id].quantity=0;}
                this.cart.splice(id,1);
                this.savingCart();
                console.log(ticket);
                }},
        savingCart(){
            const parsed = JSON.stringify(this.cart);
            sessionStorage.setItem('cart', parsed);
        },
    }, 

})

const verAppVue = app.mount("#app") 