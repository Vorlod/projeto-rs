<template>
  <div>
      <div class="column is-7-desktop is-12-mobile padding-zero left-desktop">
        <div class="is-12-mobile is-6-tablet is-offset-3-tablet is-6-desktop is-offset-3-desktop is-4-fullhd is-offset-4-fullhd is-narrow">
            <div class="padding-10">
                <h3>Realiza uma validação</h3>
            </div>
            <div class="padding-10" v-if="this.erroRequisicao" style="color: red">
                Erro ao realizar requisição
            </div>
            <div class="padding-10" v-if="this.sucesso" style="color: green">
                Número de Documento Válido
            </div>
            <div class="padding-10" v-if="this.falha" style="color: red">
                Número de Documento Inválido
            </div>
            <div class="padding-10" v-if="this.erroServidor" style="color: green">
                Erro Servidor
            </div>
            <div class="padding-10">
                <label for="cliente" style="margin-right: 5px">Cliente</label>
                <input type="text"
                       class="input"
                       placeholder="Digite seu nome"
                       id="cliente"
                       autocomplete="off"
                       v-model="cliente"
                       name="cliente">
            </div>
            <div class="padding-10">
                <input type="radio" v-model="escolha" value="0">CPF
                <input type="radio" v-model="escolha" value="1">CNPJ
            </div>

            <br/>

            <input type="text"
                   class="input"
                   placeholder="Digite um cpf"
                   id="cpf"
                   v-if="escolha === '0'"
                   autocomplete="off"
                   v-model="cpf"
                   v-mask="'###.###.###-##'"
                   name="cpf">

            <input type="text"
                   class="input"
                   placeholder="Digite um cnpj"
                   id="cnpj"
                   v-if="escolha === '1'"
                   autocomplete="off"
                   v-model="cnpj"
                   v-mask="'##.###.###/####-##'"
                   name="cpf">
            <div class="padding-10">
                <button class="button" type="button" @click="goHome()">
                    Validar
                </button>
            </div>
        </div>
      </div>

      <div class="column is-7-desktop is-12-mobile padding-zero left-desktop">
          <div class="padding-10">
              <h3>Consulta por mês</h3>
          </div>
          <div class="padding-10">
              <select id="mes" name="mes" v-model="mes" class="input">
                  <option :value="null"> Selecione</option>
                  <option :value="1">
                      Janeiro
                  </option>
                  <option :value="2">
                      Fevereiro
                  </option>
                  <option :value="3">
                      Março
                  </option>
                  <option :value="4">
                      Abril
                  </option>
                  <option :value="5">
                      Maio
                  </option>
                  <option :value="6">
                      Junho
                  </option>
                  <option :value="7">
                      Julho
                  </option>
                  <option :value="8">
                      Agosto
                  </option>
                  <option :value="9">
                      Setembro
                  </option>
                  <option :value="10">
                      Outubro
                  </option>
                  <option :value="11">
                      Novembro
                  </option>
                  <option :value="12">
                      Dezembro
                  </option>
              </select>
          </div>

          <div class="padding-10">
              <button class="button" type="button" @click="goMes()">
                  Consultar
              </button>
          </div>

          <div v-for="(consulta, i) in consultaMes" :key="i" class="padding-10">

              <h3>{{ i }}</h3>
              <div v-for="(custo, i) in consulta" :key="i" class="padding-2">

                  Custo: R$ {{custo.preco}} <br/>
                  Quantidade: {{custo.qtd}}
              </div>

          </div>
      </div>
  </div>
</template>

<script>
    import {RequisicaoService} from '../services/RequisicaoService'

export default {
  name: 'HelloWorld',
    data() {
        return {
            escolha: '0',
            cpf: '',
            cnpj: '',
            cliente: '',
            mes: null,
            consultaMes: [],
            erroRequisicao: false,
            sucesso: false,
            falha: false,
            erroServidor: false

        }
    },
  props: {
    msg: String
  },
  methods: {
      goHome: function () {

          this.falha = false;
          this.sucesso = false;

          if(this.cliente === ''){
              this.erroRequisicao = true;
              return;
          }

          if(this.cpf === '' && this.cnpj === ''){
              this.erroRequisicao = true;
              return;
          }

          if (this.escolha === '0'){
              let acessoData = {
                  cliente: this.cliente,
                  numDoc: this.cpf,
              };
              new RequisicaoService(this.$http).requisicao(acessoData).then(
                  response => {
                      if(response.body){
                          this.sucesso = true;
                      }else{
                          this.falha = true;
                      }
                      this.erroRequisicao = false;
                  },
                  error => {
                      this.erroRequisicao = true;
                      console.log("Status Error " + error.status);
                  }
              );
          }else{
              let acessoData = {
                  cliente: this.cliente,
                  numDoc: this.cnpj,
              };
              new RequisicaoService(this.$http).requisicao(acessoData).then(
                  response => {
                      if(response.body){
                          this.sucesso = true;
                      }else{
                          this.falha = true;
                      }
                      this.erroRequisicao = false;
                  },
                  error => {
                      this.erroRequisicao = true;
                      console.log("Status Error " + error.status);
                  }
              );
          }



      },
      goMes: function () {

          if(this.mes === null || this.mes === ''){
              this.erroRequisicao = true;
              return;
          }
          new RequisicaoService(this.$http).requisicaoMes(this.mes).then(
                  response => {

                      this.consultaMes = response.body;
                      console.log(response.body);
                      this.erroRequisicao = false;
                  },
                  error => {
                      this.erroRequisicao = true;
                      console.log("Status Error " + error.status);
                  }
              );
          }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.padding-10{
    padding: 10px 10px 10px 10px;
}

.padding-2{
    padding: 2px 2px 2px 2px;
}

.input {
    border-radius: 5px;
    box-shadow: 0 0 5px #ddd;
}
</style>
