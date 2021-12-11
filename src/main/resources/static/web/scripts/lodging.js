const app = Vue.createApp({

    data() {
        return {
            hotels: [],
            hotel: true,
            parking: false,
            concierge: false,
            beds: 1,
            nights: 1,
            cart:[]

        }
    },

    created() {
        axios.get('/api/products/hotels')
        
        .then(response => {
            console.log(response.data)
            this.hotels= response.data
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
        hotelShow(product) {
            this.hotel = product;
            console.log(product);
        },
        haveParking(hotel) {
            if (hotel == true) {
                return "Yes"
            } else {
                return "No"
            }

        },
        addToCart(hotel){
           console.log(hotel.productId);
           axios.post("/api/clients/current/addToCart/hotel",`hotelId=${hotel.productId}&nights=${this.nights}&passangers=${this.beds}`)
           .then(resp => {
                hotel = resp.data;
                console.log(hotel);
                if (hotel.stock <= 0) {
                   alert("No stock");
                   } else {
                         hotel.stock--;
                         console.log(this.cart);
                         if (this.cart.some(prod => prod.id == hotel.id)) {
                            let id = this.cart.findIndex(prod => prod.id == hotel.id);
                            //actualizar cantidad en ese producto
                            this.cart[id].quantity+= this.beds*this.nights;
                            } else {
                              hotel.quantity = this.beds*this.nights;
                              hotel.clase = this.clase;
                              hotel.subtotal = hotel.quantity * hotel.price;
                              this.cart.push(hotel);
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
