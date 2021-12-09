const app = Vue.createApp({

    data() {
        return {
            events: [],
            event: true,
            vip: true
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
        }     
    }, 

})

const verAppVue = app.mount("#app") 