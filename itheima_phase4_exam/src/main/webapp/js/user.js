new Vue({
    el: "#app",
    data: {
        user: {id:0, userName: '', password: '', email: 0, phoneNum: ''},
        userList: []
    },
    methods:{
        findAll: function () {
            // 不能直接在axios中使用this。
            var that = this;
            axios.get("/phase4Exam/user/findAll").then(function (response) {
                that.userList = response.data;
            }).catch(function (error) {
                console.log(error);
            })
        },
        findById: function (id) {
            var that = this;
            axios.get("/phase4Exam/user/findById/" + id).then(function (response) {
                that.user = response.data;
                $("#myModal").modal("show");
            }).catch(function (error) {
                console.log(error);
            });
        },
        update: function () {
            var that = this;
            axios.post("/phase4Exam/user/update", that.user).then(function () {
                that.findAll();
            }).catch(function (error) {
                console.log(error);
            })
        }
    },
    created(){
        this.findAll();
    }
});