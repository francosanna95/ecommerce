const app = Vue.createApp({
      data() {
          return {
              cart:[]
          }
      },
      created() {
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