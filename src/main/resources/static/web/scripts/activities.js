const app = Vue.createApp({

    data() {
        return {
            events: [],
            event: true,
            vip: true,
            personAmount: 1,
            cart:[]
        }
    },

    created() {

        axios.get('/api/products/events')
        
        .then(response => {
            console.log(response.data)
            this.events = response.data
        })
        .catch(error => {
            return error.message;
        })
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
        activityShow(product) {
            this.event = product;
            console.log(product);
        },
        haveVip(event) {
            console.log(this.vip)
            if (event == true) {
                return "Yes"
            } else {
                return "No"
            }
        },
        addToCart(ticket){
          console.log(ticket.productId);
          axios.post("/api/clients/current/addToCart/event",`eventId=${this.event.productId}&isVip=${true}&attendants=${this.personAmount}`)
                       .then(resp => {
                             ticket = resp.data;
                             console.log(ticket);
                             if (ticket.stock <= 0) {
                                 alert("No stock");
                             } else {
                               ticket.stock--;
                               console.log(this.cart);
                               if (this.cart.some(prod => prod.id == ticket.id)) {
                                  let id = this.cart.findIndex(prod => prod.id == ticket.id);
                                  console.log(id);
                                  //actualizar cantidad en ese producto
                                  this.cart[id].quantity= this.cart[id].quantity + this.personAmount;
                               } else {
                                 this.event.quantity = this.personAmount;
                                 ticket.subtotal = this.event.quantity * this.event.price;
                                 this.cart.push(ticket);
                                 console.log(this.cart);
                                 }
                             this.savingCart();
                    }})
                    .catch(err=> console.log(err));
        },
         savingCart(){
                    const parsed = JSON.stringify(this.cart);
                    sessionStorage.setItem('cart', parsed);
                },
    }, 

})

const verAppVue = app.mount("#app") 