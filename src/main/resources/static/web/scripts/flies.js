const app = Vue.createApp({

    data() {
        return {
            tickets: [],
            chart:[],
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
                    this.chart = JSON.parse(sessionStorage.getItem('cart'));
                } catch (e) {
                    sessionStorage.removeItem('cart');
                }
                console.log(this.chart)
            };
        },
    methods: {
        addToChart(ticket){
        if(ticket.stock<=0){
            alert("No stock");}
        else{
            ticket.stock--;
            if(this.chart.some(prod=>prod.productId==ticket.productId)){
            ticket.quantity++;}
            else{ticket.quantity=1;
            this.chart.push(ticket);}
            console.log(this.chart);
            this.savingChart();
        };
        },
        savingChart(){
            const parsed = JSON.stringify(this.chart);
            sessionStorage.setItem('cart', parsed);
        },
    }, 

})

const verAppVue = app.mount("#app") 