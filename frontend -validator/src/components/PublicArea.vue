<template>
  <div class="wrapper">

    <div class="container-conteudo">
      <main class="container div-conteudo">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>

  export default {
  }

</script>

<style>

  @media only screen and (min-width: 768px) {

    .container-conteudo {
      margin-right: auto;
      margin-left: auto;
      padding-left: 6px;
      padding-right: 6px;
      min-height: 50vh;
    }

    .box h1 {
      font-size: 1.6em;
      margin-top: 20px;
      font-weight: 700;
    }

    .div-conteudo {
      width: 100%;
      height: 50px;
      position: relative;
      top: 56px;
    }

    @media print {
      body, body * {
        visibility: hidden;
      }

      .container-conteudo, .container-conteudo * {
        visibility: hidden;
      }

      .menu-left, .menu-left * {
        visibility: hidden;
      }

      .conteudo-nav {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
      }
    }
  }
</style>
