<template>
  <div id="header">
    <img id="logo" src="../assets/java.png" style="height: 45px"/>
    <span class="title">JVM Class Loading</span>
  </div>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>

  #header{
    height:7%;
    display: flex;
    align-items: Center;
    border-bottom: 1px solid #78787840;
  }

  #logo{
    margin-top: 5px;
  }

  .title{
    color: #ffffff;
    font-size: 1.9em;
    font-family: -webkit-pictograph;
  }

</style>
