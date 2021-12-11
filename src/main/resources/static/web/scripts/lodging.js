const app = Vue.createApp({

    data() {
        return {
            hotels: [],
            hotel: true,
            parking: false,
            concierge: false,
            beds: 1,
            nights: 1

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
           axios.post("/api/clients/current/addToCart/hotel",`hotelId=${hotel.productId}&arrivalDate=${this.clase}&passengers=${this.passengers}`)
           .then(resp => {
                hotel = resp.data;
                console.log(hotel);
                if (ticket.stock <= 0) {
                   alert("No stock");
                   } else {
                         hotel.stock--;
                         console.log(this.cart);
                         if (this.cart.some(prod => prod.id == hotel.id)) {
                            let id = this.cart.findIndex(prod => prod.id == hotel.id);
                            //actualizar cantidad en ese producto
                            this.cart[id].quantity= hotel.quantity;
                            } else {
                              ticket.quantity = this.passengers;
                              ticket.clase = this.clase;
                              ticket.subtotal = hotel.quantity * hotel.price;
                              this.cart.push(hotel);
                              console.log(this.cart);
                              }
                         this.savingCart();
                   }})
                   .catch(err=> console.log(err));
        },

    }, 

})

const verAppVue = app.mount("#app") 
