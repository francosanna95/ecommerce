const app = Vue.createApp({

    data() {
        return {
            clients: [],
            email: "",
            password: "",
            cart: [],
            currentUser: [],

            firstName: "",
            lastName: "",
            roleUser: "",
            isPasswordVisible: false,
            isAdmin: false,
            isClient: false
        }
    },

    created() {
        axios.get("/api/clients/current")
            .then(response => {
                console.log(response.data)
                this.currentUser = response.data
                if (response.data.role == "CLIENT") {
                    this.isClient = true;
                    this.isAdmin = false;
                } else if (response.data.role == "AGENCY") {
                    this.isClient = false;
                    this.isAdmin = true;
                }
            })
    },
    methods: {
        login(e) {
            if (e) {
                e.preventDefault()
            }


            axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    console.log(response)
                    window.location.reload()
                })
                .catch(error => {
                    console.log(error.response.status)
                    console.log(error.response.data)
                })
        },
        logout() {
            axios.post('/api/logout')
                .then(response => {
                    sessionStorage.removeItem('cart'); //alerta de exito
                    console.log("loged out!");
                    this.isClient = false;
                    this.isAdmin = false;
                    window.location.reload();
                })
                .catch(error => {
                    console.log('Error', error.message);
                })
        },
    },

})
