<template>
    <div class="container-forms">
      <h1>Criar novo usuário</h1>
      <Input :type="'email'" :placeholder="'Type your email'" :label="'E-mail'" v-model="email"/> 
      <Input :type="'password'" :placeholder="'Type your password'" :label="'Password'" v-model="password"/> 
      <button @click="saveUser">Salvar</button>
    </div>
    <Alert class="alerta" v-if="showAlerta" :message="messageAlerta"/>

</template>

<script setup lang="ts">
  import Input from '@/components/Input.vue';
  import { onMounted, ref } from 'vue';
  import Alert from '@/components/Alert.vue';
  import axios from 'axios';
  const email = ref<string>('');
  const password = ref<string>('');
  const showAlerta = ref<boolean>(false);
  const messageAlerta = ref<string>('');

  const saveUser = async () =>{
    if(email.value == '' || password.value == ''){ 
      mostrarAlerta("Os campos precisam estar preenchidos.")
      return;
    }
    await cadastrarUser();
    limparCampos();
  }

  const mostrarAlerta = (message: string) => {
    messageAlerta.value = message;
    showAlerta.value = true;

    setTimeout(()=> {
      showAlerta.value = false;
      messageAlerta.value = "";
    }, 3000);
  }

  const cadastrarUser = async () =>{
    try{
      const body = {
        email: email.value,
        password: password.value
      }
      const response = await axios.post("http://localhost:8080/users/register", body);

      if(response.status == 200){
        mostrarAlerta(response.data);
      }else{
        mostrarAlerta(response.data)
      }
    }catch(error){
      mostrarAlerta(error.response.data.message);
    }
  }

  const limparCampos = () => {
    email.value = '';
    password.value = '';
  }
</script>

<style scoped>
.about{
  display: flex;
  align-items: center;
}
h1{
  color:  #00bD7E;
  font-weight: 500;
  font-size: 2rem;
  top: -10px;
}
.container-forms{
  background-color: #2f1e40;
  border-radius: 2em;
  padding: 2em;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30vw;
  box-shadow: 0px 4px 10px rgba(56, 56, 56, 0.6);
}
button {
    margin: 2rem 0 0 0;
    width: 20vw;
    display: inline-block;
    padding: 12px 24px;
    font-size: 16px;
    font-weight: 600;
    color: black;
    background-color: #00bD7E;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  }

  button:hover {
    background-color: #00bD7E;
    box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.2);
  }

  button:active {
    background-color: #00bD7E;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    transform: translateY(2px);
  }

  button:disabled {
    background-color: #d6d6d6;
    color: black;
    cursor: not-allowed;
    box-shadow: none;
  }
</style>