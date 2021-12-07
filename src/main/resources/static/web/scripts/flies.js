const app = Vue.createApp({

    data() {
        return {
            tickets: [],
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
        addToCart(ticket){
        if(ticket.stock<=0){
            alert("No stock");}
        else{
            ticket.stock--;
            if(this.cart.some(prod=>prod.productId==ticket.productId)){
            ticket.quantity++;}
            else{ticket.quantity=1;
            this.cart.push(ticket);}
            console.log(this.cart);
            this.savingCart();
        };
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