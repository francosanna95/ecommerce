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
        totalProductsInCart() {
                    const array = this.cart
                    function reducer(previous, current, index, array) {
                      const product = array[index];
                      const returns = previous + (parseInt(product.quantity));
                      return returns;
                    }
                    return array.reduce(reducer, 0);
        },
        totalPriceCalc() {
                    const array = this.cart
                    function reducer(previous, current, index, array) {
                        const product = array[index];
                        const returns = previous + (product.finalPrice*product.quantity);
                        return returns;
                    }
                    return array.reduce(reducer, 0);
                },
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
         removeOne(prod){
                         console.log(prod);
                         console.log(typeof prod.id)
                             let findProd=this.cart.findIndex(product=>product.id==prod.id);
                             console.log(findProd);
                         axios.post("/api/clients/current/removeFromCart",`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
                         .then(resp=>{ console.log(findProd)
                                       this.cart[findProd].quantity--;
                                       if(this.cart[findProd].quantity<=0){this.cart.splice(findProd,1)}
                                       this.savingCart();
                                       if(this.cart.length==0){
                                          sessionStorage.removeItem('cart');}}).catch(err=>console.log(err))
                 },
                 removeAll(prod){
                         axios.post('/api/clients/current/finalRemoveFromCart',`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
                         .then(resp=>{ if(this.cart.some(product=>product.id==prod.id)){
                             let index=this.cart.findIndex(product=>product.id==prod.id);
                             console.log(index);
                             this.cart.splice(index,1);
                             this.savingCart();
                             if(this.cart.length==0){
                               sessionStorage.removeItem('cart');}
                             }
                         }).catch(err=>console.log(err))
                 },
                 addProduct(prod){
                         axios.post("/api/clients/current/add1toCart",`userProductId=${prod.id}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' }})
                         .then(resp=>{
                             let id=this.cart.findIndex(product=>product.id==prod.id);
                             console.log(id)
                             console.log(this.cart)
                              this.cart[id].quantity++;
                              this.savingCart();

                         })
                 },
    }, 

})

const verAppVue = app.mount("#app") 