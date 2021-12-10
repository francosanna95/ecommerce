const app = Vue.createApp({

    data() {
        return {
            tickets: [],
            ticket: true,
            cart:[],
            clase: "",
            passengers: 1,
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
         if (sessionStorage.getItem('cart')) {
               try {
                    console.log(this.cart);
                    this.cart = JSON.parse(sessionStorage.getItem('cart'));
                            console.log(this.cart);
                        } catch (e) {
                            sessionStorage.removeItem('cart');
                        }
                        this.cart=sessionStorage.getItem('cart')
                        console.log(this.cart)

                    }
    },
    mounted() {
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
            axios.post("/api/clients/current/addToCart/ticket",`ticketId=${ticket.productId}&clase=${this.clase}&passengers=${this.passengers}`)
            .then(resp=>{
                ticket=resp.data;
                console.log(ticket);
                if(ticket.stock<=0){
                   alert("No stock");}
                   else{
                     ticket.stock--;
                    console.log(this.cart);
                     if(this.cart.some(prod=>prod.productId==ticket.productId)){
                       ticket.quantity=ticket.quantity+this.passengers;}
                     else{ticket.quantity=this.passengers;}
                     ticket.clase=this.clase;
                ticket.subtotal=ticket.quantity*ticket.price;
                this.cart.push(ticket);
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