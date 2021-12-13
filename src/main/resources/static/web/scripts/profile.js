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
        getImgUrl() {
            var myWidget = cloudinary.createUploadWidget({
                cloudName: 'melbastrips',
                uploadPreset: 'testing',
                folder: 'Services'
            }, (error, result) => {
                if (!error && result && result.event === "success") {
                    console.log('Done! Here is the image info: ', result.info);
                    console.log(result.info.secure_url);
                    this.updateProfileImg(result.info.secure_url)
                }
            }
            )


            myWidget.open();


        },
        updateProfileImg(imgUrl) {
            axios.patch("/api/clients/current/updatePicture", `imgUrl=${imgUrl}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(res => {
                    if (res.status == 200) {
                        swal(`We just updated your profile pic!`, {
                            buttons: "Great!",
                            icon: "success"
                        })
                            .then(res => {
                                if (res) {
                                    window.location.reload()
                                }
                            })
                    }
                })

        }
    },

})
app.mount("#app")

var myWidget = cloudinary.createUploadWidget({
    cloudName: 'melbastrips',
    uploadPreset: 'testing',
    folder: 'Profiles'
}, (error, result) => {
    if (!error && result && result.event === "success") {
        console.log('Done! Here is the image info: ', result.info);
    }
}
)

document.getElementById("upload_widget").addEventListener("click", function () {
    myWidget.open();
}, false);


