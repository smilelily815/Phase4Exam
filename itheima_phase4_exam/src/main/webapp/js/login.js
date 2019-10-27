new Vue({
    el: "#app",
    data: {
        user: {username: '', password: ''},
        message: ""
    },
    methods: {
        onSubmit: function () {
            var that = this;
            axios.post("/phase4Exam/user/login", that.user).then(function (response) {
                var data = response.data;
                if (data == true) {
                    window.location.href = "/phase4Exam/user.html";
                } else {
                    that.message = "账号密码错误"
                }
            }).catch(function (error) {
                console.log(error);
            })
        }
    }
});