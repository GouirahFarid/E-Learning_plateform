<template>
    <form class="my-4" @submit="formValidation" enctype="multipart/form-data">
        <div class="row ">
            <div class="col-12 text-center my-1"><h4>My Account</h4></div>
            <div class="col-12 mb-1">
                <div class="form-group">
                    <label class="h5" for="title">Username</label>
                    <input  readonly :value="currentUser.username" type="text" class="form-control p-2" id="title"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="firstName">First Name:</label>
                    <input readonly :value="currentUser.firstName" type="text" class="form-control p-2" id="firstName" />
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="newFirstName">New First name:</label>
                    <input v-model="updateAccount.firstName" type="text" class="form-control p-2" id="newFirstName" placeholder=" new first name"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="secondName">Second Name:</label>
                    <input readonly :value="currentUser.secondName" type="text" class="form-control p-2" id="secondName"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="newSecondName">New Second Name:</label>
                    <input v-model="updateAccount.secondName" type="text" class="form-control p-2" id="newSecondName" placeholder="Second name"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="mail">E-mail</label>
                    <input readonly :value="currentUser.mail" type="email" class="form-control p-2" id="mail"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="newMail">New E-mail:</label>
                    <input v-model="updateAccount.mail" type="email" class="form-control p-2" id="newMail" placeholder="exemple@email.com"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="oldPassword"> Old password:</label>
                    <input v-model="updateAccount.oldPassword" type="password" class="form-control p-2" id="oldPassword"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="newPassword">New  password</label>
                    <input v-model="updateAccount.NewPassword" type="password" class="form-control p-2" id="newPassword"/>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="des">Biography</label>
                    <textarea readonly :value="currentUser.biography" class="form-control p-2" id="des" rows="3"></textarea>
                </div>
            </div>
            <div class="col-6 mb-1">
                <div class="form-group">
                    <label class="h5" for="description">New biography</label>
                    <textarea v-model="updateAccount.biography" class="form-control p-2" id="description" rows="3"></textarea>
                </div>
            </div>
            <div class="col-12 text-center">
                <button class="btn btn-block">Update</button>
            </div>
        </div>
    </form>
</template>

<script>
    export default {
        name: "MyAccount",
        data(){
            return{
                updateAccount:{
                    firstName:'',
                    secondName:'',
                    mail:'',
                    oldPassword:'',
                    NewPassword:'',
                    biography: ''
                },
                currentUser:this.$store.state.auth.userState.user,
                formErrors: []
            }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let account = new FormData();
                    account.append('firstName', this.updateAccount.firstName);
                    account.append('secondName', this.updateAccount.secondName);
                    account.append('mail', this.updateAccount.mail);
                    account.append('newPassword',this.updateAccount.NewPassword);
                    account.append('oldPassword',this.updateAccount.oldPassword);
                    account.append('biography',this.updateAccount.biography)
                    account.append('personId',this.currentUser.id)
                    this.$store.dispatch("auth/updatePerson",account)
                        .then(this.$router.push(`/`));
                    return true;
                }
            },
        }
    }
</script>

<style scoped>
 form{
     width: 70%;
 }
 .form-control{
        background-color: #b3e4e5;
    }
 .btn-block{
     background-color: #efb932;
     font-weight: bold;
 }
</style>