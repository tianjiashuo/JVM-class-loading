<template>
  <div id="start">
    <div id="leftDiv">
      <img src="../assets/java.png" style="height: 80px; margin-left: 25px"/>
      <div id="text">
        <span class="textTitle">JVM Class Loading</span>
        <p class="textContent">
          The purpose of this project is to provide a more intuitive and detailed GC control tool to help monitor,
          manage and improve the GC algorithm.
        </p>
      </div>
      <button class="startButton" @click="startClick">Start</button>
    </div>

    <div id="transBottom"></div>

    <div id="rightDiv">
      <div class="heapDiv">
        <div class="demoHeader">
          <span class="demoName">HEAP</span>
          <button class="demoButton">show</button>
        </div>
        <div class="demoBody">
          <ul class="demoData">
            <li class="demoData2">549 83768 [Ljava.util.Hashtable$Entry;</li>
            <li class="demoData3">2070 82800 java.util.WeakHashMap$Entry</li>
            <li class="demoData1">2572 82304 java.sql.Timestamp</li>
          </ul>
        </div>
      </div>

      <div class="stackDiv">
        <div class="demoHeader">
          <span class="demoName">STACK</span>
          <button class="demoButton">show</button>
        </div>
        <div class="demoBody">
          <ul class="demoData">
            <li class="demoData3">LOCALVARIABLE result I L2 L3 2</li>
            <li class="demoData1">MAXSTACK = 2</li>
            <li class="demoData2">MAXLOCALS = 3</li>
          </ul>
        </div>
      </div>

      <div class="methodAreaDiv">
        <div class="demoHeader">
          <span class="demoName">METHOD AREA</span>
          <button class="demoButton">show</button>
        </div>
        <div class="demoBody">
          <ul class="demoData">
            <li class="demoData1">java.lang.Thread .getStackTrace</li>
            <li class="demoData2">com.jvm.demo.service.printMethod .printTrack2</li>
            <li class="demoData3">com.jvm.demo.service.add .add</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "StartPage",
      data(){
        return{

        }
      },
      methods:{
        startClick(){
          let that = this;
          $(".heapDiv").addClass("heapDivDisapper");
          setTimeout(function () {
            $('.heapDiv').css('margin-left', '80%');
            $('.heapDiv').css('visibility', 'hidden');
          }, 1000);
          setTimeout(function () {
            $(".stackDiv").addClass("stackDivDisapper");
          }, 700);
          setTimeout(function () {
            $('.stackDiv').css('margin-right','-38%');
            $('.stackDiv').css('visibility', 'hidden');
          }, 1600);
          setTimeout(function () {
            $(".methodAreaDiv").addClass("methodAreaDivDisapper");
          }, 900);
          setTimeout(function () {
            $('.methodAreaDiv').css('margin-left', '80%');
            $('.methodAreaDiv').css('visibility', 'hidden');
          }, 1900);
          setTimeout(function () {
            that.$router.push({
              name: 'CodePage'
            });
          }, 1950);

        }
      }
    }
</script>

<style scoped>
  #start{
    height: 100%;
    margin: 0;
    background:url(../assets/background.jpg) no-repeat;
    background-size:100% 100%;
    display: flex;
    flex-direction: row;
    padding-left: 10%;
    padding-right: 10%;
  }

  #leftDiv{
    margin-top: 10%;
    height: 65%;
    flex: 1;
  }

  #text{
    margin-top: 5%;
    padding-left: 38px;
  }

  .textTitle{
    color: #ffffff;
    font-size: 2.5em;
    font-weight: bold;
  }

  .textContent{
    color: #c7c9d3;
    font-size: 1.2em;
    line-height: 1.5;
  }

  .startButton{
    background: #47cf73;
    color: #000000;
    border-radius: 5px;
    font-size: 2em;
    width: 33%;
    height: 10%;
    margin: 7%;
    cursor:pointer;
    border: 0;
  }
  .startButton:hover{
    background: #3eb15e;
  }

  #rightDiv{
    margin-top: 10%;
    height: 65%;
    flex: 1;
    padding-left: 5%;
    padding-right: 5%;
    z-index: 2;
  }

  .heapDiv{
    visibility:visible;
    background: #1d1e22;
    width: 300px;
    height: 146px;
    margin: 0 auto;
    box-shadow: 15px 10px 20px #101011b0;
  }
  .heapDivDisapper{
    opacity: 1;
    margin-left: 0;
    -webkit-animation: heapDivMove 1s linear,heapDivDis 0.5s linear 0.5s;
  }
  @-webkit-keyframes heapDivMove{
    from{margin-left: 0}
    to{margin-left: 80%}
  }
  @-webkit-keyframes heapDivDis{
    from{opacity: 1}
    to{opacity: 0}
  }


  .stackDiv{
    visibility:visible;
    background: #1d1e22;
    width: 300px;
    height: 146px;
    float: right;
    margin: 20px;
    box-shadow: 15px 10px 20px #101011b0;
  }
  .stackDivDisapper{
    opacity: 1;
    margin-right: 20px;
    -webkit-animation: stackDivMove 0.9s linear,stackDivDis 0.5s linear 0.4s;
  }
  @-webkit-keyframes stackDivMove{
    from{ margin-right: 20px}
    to{ margin-right: -38%}
  }
  @-webkit-keyframes stackDivDis{
    from{opacity: 1}
    to{opacity: 0}
  }


  .methodAreaDiv{
    visibility:visible;
    background: #1d1e22;
    width: 300px;
    height: 146px;
    float: left;
    box-shadow: 15px 10px 20px #101011b0;
  }
  .methodAreaDivDisapper{
    opacity: 1;
    margin-left: 0;
    -webkit-animation: methodAreaDivMove 1s linear,methodAreaDivDis 0.5s linear 0.5s;
  }
  @-webkit-keyframes methodAreaDivMove{
    from{margin-left: 0}
    to{margin-left: 80%}
  }
  @-webkit-keyframes methodAreaDivDis{
    from{opacity: 1}
    to{opacity: 0}
  }


  .demoHeader{
    height: 19px;
    background-color: #1a1b1f;
    color: #aaaebc;
    font-size: 0.8em;
    border-bottom: 2px solid #78787840;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 2px;
  }

  .demoBody{
    height: 265px;
    font-size: 0.85em;
  }

  .demoData1{
    color: #9f8198;
  }
  .demoData2{
    color: #e1ca72;
  }
  .demoData3{
    color: #7a99ad;
  }

  .demoButton{
    border-radius: 5px;
    float: right;
    font-size: 0.8em;
    color: white;
    background-color: #444857;
    cursor:pointer;
    border: 0;
  }

  #transBottom{
    width: 128%;
    height: 126%;
    background: #c7c9d357;
    opacity: 0.3;
    position: absolute;
    transform: rotate(-57deg);
    left: 15%;
  }

</style>
