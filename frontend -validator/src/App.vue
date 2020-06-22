<template>
  <div id="app">
    <header>
      <nav class=" fixed-top">

        <div class="navbar-blue row">
          <div class="col-8 col-lg-10 pull-left is-hidden-touch">
            <h1 class="col-12 title-big"><strong>VALIDAÇÃO DE CPF/CNPJ</strong></h1>
            <h1 class="col-12 title-small">Não é grátis - Custo R$ 0,10</h1>
          </div>

          <div class="col-8 pull-left is-hidden-desktop">
            <h1 class="col-12 title-big-mobile"><strong>PROJETO</strong></h1>
          </div>

        </div>

      </nav>

    </header>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
