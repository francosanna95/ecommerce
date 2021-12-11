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
    }, 

})

const verAppVue = app.mount("#app") 
