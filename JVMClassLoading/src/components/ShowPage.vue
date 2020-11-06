<template>
  <div id="showPage">

    <Header/>

    <!--		代码展示区-->
    <div id="inputCodeBox">
      <div class="inputHeader">
        <span class="methodName">Input Area </span>
      </div>

      <div class="inputCode">

        <div class="editor_control_2">
          <textarea id="codeInputBox" ref="textarea"></textarea>
        </div>
      </div>
    </div>

    <!--		可视化区-->
    <div id="loadingArea">

      <div class="left2">
        <!--			虚拟栈/Java栈-->
        <div class="VMStack">
          <div class="methodHeaderLeft">
            <span class="methodName">VM STACK</span>
            <button class="buttonShow">show</button>
          </div>
          <div id="vmStackBody" class="methodBody">

          </div>
        </div>

        <!--			方法区-->
        <div class="methodArea">
          <div class="methodHeaderLeft">
            <span class="methodName">METHOD AREA</span>
            <button class="buttonShow" onclick="getMethodarea()">show</button>
          </div>
          <div id="methodAreaBody" class="methodBody">
            <!--						<div id="demo1">-->
            <!--							<ol id="methodAreaData"></ol>-->
            <!--						</div>-->
          </div>
        </div>
      </div>

      <div class="right3">
        <!--			堆-->
        <div class="heap">
          <div class="methodHeaderRight">
            <span class="methodName">HEAP</span>
            <button class="buttonShow">show</button>

          </div>
          <div id="heapBody" class="methodBody">
            <!--                        <div class="test">int i = 0;</div>-->
          </div>
        </div>

        <!--			本地方法栈-->
        <div class="NMStack">
          <div class="methodHeaderRight">
            <span class="methodName">NATIVE METHOD STACK</span>
            <button class="buttonShow">show</button>

          </div>
          <div class="methodBody">

          </div>
        </div>

        <!--			程序计数器-->
        <div class="PCRegister">
          <div class="methodHeaderRight">
            <span class="methodName">PC REGISTER</span>
            <button class="buttonShow">show</button>

          </div>
          <div class="methodBody">

          </div>
        </div>
      </div>


    </div>

  </div>
</template>

<script>
    import Header from "./Header";
    // 引入全局实例
    import _CodeMirror from 'codemirror'
    // 核心样式
    import '../css/codemirror.css'
    // 引入主题后还需要在 options 中指定主题才会生效
    import '../css/cobalt.css'

    // 需要引入具体的语法高亮库才会有对应的语法高亮效果
    // codemirror 官方其实支持通过 /addon/mode/loadmode.js 和 /mode/meta.js 来实现动态加载对应语法高亮库
    // 但 vue 貌似没有无法在实例初始化后再动态加载对应 JS ，所以此处才把对应的 JS 提前引入
    import 'codemirror/mode/javascript/javascript.js'
    import 'codemirror/mode/css/css.js'
    import 'codemirror/mode/xml/xml.js'
    import 'codemirror/mode/clike/clike.js'
    import 'codemirror/mode/markdown/markdown.js'
    import 'codemirror/mode/python/python.js'
    import 'codemirror/mode/r/r.js'
    import 'codemirror/mode/shell/shell.js'
    import 'codemirror/mode/sql/sql.js'
    import 'codemirror/mode/swift/swift.js'
    import 'codemirror/mode/vue/vue.js'

    // 尝试获取全局实例
    const CodeMirror = window.CodeMirror || _CodeMirror;

    export default {
      name: "ShowPage",
      components: {
          Header
      },
      props: {
        // 外部传入的内容，用于实现双向绑定
        value: String,
        // 外部传入的语法类型
        language: {
          type: String,
          default: null
        }
      },
      data(){
        return{
          testMsg:{
            "1": null,
            "2": null,
            "3": {
              "area": "MethodArea",
              "type": null,
              "classname": "com/jvm/demo/source/Add",
              "pool": [
                0,
                1
              ],
              "method": [
                "addcd()",
                "add()"
              ],
              "param": null,
              "value": null,
              "fieldName": null
            },
            "4": {
              "area": "MethodArea",
              "type": "int",
              "classname": null,
              "pool": null,
              "method": null,
              "param": null,
              "value": "0",
              "fieldName": "a"
            },
            "5": {
              "area": "MethodArea",
              "type": "int",
              "classname": null,
              "pool": null,
              "method": null,
              "param": null,
              "value": "1",
              "fieldName": "b"
            },
            "6": null,
            "7": {
              "area": "Heap",
              "name": "c",
              "value": "3",
              "type": "int"
            },
            "8": {
              "area": "Heap",
              "name": "d",
              "value": "4",
              "type": "int"
            },
            "9": null,
            "10": null,
            "11": null,
            "12": null,
            "13": null,
            "14": null,
            "15": null,
            "16": null,
            "17": null,
            "18": {
              "area": "MethodArea",
              "type": "void",
              "classname": null,
              "pool": null,
              "method": [
                "main"
              ],
              "param": [
                "String[] args"
              ],
              "value": null,
              "fieldName": null
            },
            "19": {
              "area": "JvmStack",
              "referenceName": null,
              "type": null,
              "methodName": "add",
              "args": null
            },
            "20": {
              "area": "JvmStack",
              "referenceName": "add",
              "type": "Add",
              "methodName": null,
              "args": null
            },
            "21": {
              "area": "Heap",
              "name": "add",
              "value": null,
              "type": "Add"
            },
            "22": {
              "area": "JvmStack",
              "referenceName": null,
              "type": "int",
              "methodName": "addcd",
              "args": null
            },
            "23": null
          },
          testMsgLen:23,
          // 内部真实的内容
          // 内部真实的内容
          code: '',
          // 默认的语法类型
          mode: 'javascript',
          // 编辑器实例
          coder: null,
          // 默认配置
          options: {
            // 缩进格式
            tabSize: 2,
            // 主题，对应主题库 JS 需要提前引入
            theme: 'cobalt',
            // 显示行号
            lineNumbers: true,
            line: true
          },
          // 支持切换的语法高亮类型，对应 JS 已经提前引入
          // 使用的是 MIME-TYPE ，不过作为前缀的 text/ 在后面指定时写死了
          modes: [{
            value: 'css',
            label: 'CSS'
          }, {
            value: 'javascript',
            label: 'Javascript'
          }, {
            value: 'html',
            label: 'XML/HTML'
          }, {
            value: 'x-java',
            label: 'Java'
          }, {
            value: 'x-objectivec',
            label: 'Objective-C'
          }, {
            value: 'x-python',
            label: 'Python'
          }, {
            value: 'x-rsrc',
            label: 'R'
          }, {
            value: 'x-sh',
            label: 'Shell'
          }, {
            value: 'x-sql',
            label: 'SQL'
          }, {
            value: 'x-swift',
            label: 'Swift'
          }, {
            value: 'x-vue',
            label: 'Vue'
          }, {
            value: 'markdown',
            label: 'Markdown'
          }]
        }
      },
      mounted () {
        //接收到传来的代码
        this.code = this.$route.params.code;
        this.mode = this.$route.params.mode;
        //接收后再初始化
        this._initialize();
        this.showInit();
      },
      methods: {
        // 初始化
        _initialize() {
          // 初始化编辑器实例，传入需要被实例化的文本域对象和默认配置
          this.coder = CodeMirror.fromTextArea(this.$refs.textarea, this.options);
          // 编辑器赋值
          this.coder.setValue(this.value || this.code);

          // 支持双向绑定
          this.coder.on('change', (coder) => {
            this.code = coder.getValue();

            if (this.$emit) {
              this.$emit('input', this.code)
            }
          });

          // 尝试从父容器获取语法类型
          if (this.language) {
            // 获取具体的语法类型对象
            let modeObj = this._getLanguage(this.language);

            // 判断父容器传入的语法是否被支持
            if (modeObj) {
              this.mode = modeObj.label
            }
          }
        },
        // 获取当前语法类型
        _getLanguage(language) {
          // 在支持的语法类型列表中寻找传入的语法类型
          return this.modes.find((mode) => {
            // 所有的值都忽略大小写，方便比较
            let currentLanguage = language.toLowerCase();
            let currentLabel = mode.label.toLowerCase();
            let currentValue = mode.value.toLowerCase();

            // 由于真实值可能不规范，例如 java 的真实值是 x-java ，所以讲 value 和 label 同时和传入语法进行比较
            return currentLabel === currentLanguage || currentValue === currentLanguage
          })
        },
        // 更改模式
        changeMode(val) {
          // 修改编辑器的语法配置
          this.coder.setOption('mode', `text/${val}`);
          // 获取修改后的语法
          let label = this._getLanguage(val).label.toLowerCase();
          // 允许父容器通过以下函数监听当前的语法值
          this.$emit('language-change', label)
        },

        //好像是因为vue的异步同步的问题，这里渲染遇见延时会先被挂起，等到for结束后再去eventloop里找延时世间，暂时还没想好怎么改

        showInit(){
          var aniTime = 500;
          let that = this;
          for (var i = 1; i <= this.testMsgLen; i++) {
            this.showData(i,this.testMsg);
            console.log("延迟开始");
            //$("#"+i).delay(that.aniTime).fadeIn();
            //$("#"+i).fadeIn();
            this.delay(i);
            console.log("延迟结束");
            aniTime += 300;
          }
        },
        delay(i) {
          var self = this;
          //一定要创建一个timer，然后调用延时之前先清除timer的延时
          var timer;
          clearTimeout(timer);
          timer = setTimeout(function() {
            console.log('3秒后执行')
            $("#"+i).fadeIn();
          }, 300)
        },
        randomColor(){
          var temp = Math.floor(Math.random()*10);    // 可均衡获取 0 到 9 的随机整数。
          var color = "";
          if(temp < 3){
            color = "#e1ca7280";
          }else if(temp < 7){
            color = "#9f819878";
          }else{
            color = "#7a99ad73";
          }
          return color;
        },
        showData(i,testMsg){
          if(testMsg[i] != null){
            console.log("222 " + testMsg[i].area);
            switch(testMsg[i].area){
              case "JvmStack":
                //获取div
                var div = document.getElementById("vmStackBody");
                //添加子div
                var div2 = document.createElement("div");
                div2.id = i;
                div2.innerHTML ="referenceName: " + testMsg[i].referenceName  +" type: "+ testMsg[i].type +" methodName: "+testMsg[i].methodName +"<br/>";
                div2.style = "display:none; margin: auto; margin-bottom: 5px; width: 95%; font-size: 1.3em; border-radius: 5px; text-align: center;";
                div2.style.background = this.randomColor();
                div.appendChild(div2);
                break;
              case "MethodArea":
                //获取div
                var div = document.getElementById("methodAreaBody");
                //添加子div
                var div2 = document.createElement("div");
                div2.id = i;
                // div2.innerHTML = testMsg[i].type  +" "+ testMsg[i].classname +" "+testMsg[i].args + "<br/>";
                if(testMsg[i].type === null){
                  div2.innerHTML ="className: " + testMsg[i].classname +" method: "+testMsg[i].method + "<br/>";
                }else{
                  div2.innerHTML ="type: " + testMsg[i].type +" fieldName: "+testMsg[i].fieldName+ " value: " + testMsg[i].value +"<br/>";
                }
                div2.style = "display:none;margin: auto; margin-bottom: 5px; width: 95%; font-size: 1.3em; border-radius: 5px; text-align: center;";
                div2.style.background = this.randomColor();
                div.appendChild(div2);
                break;
              case "Heap":
                //获取div
                var div = document.getElementById("heapBody");
                //添加子div
                var div2 = document.createElement("div");
                div2.id = i;
                div2.innerHTML = "type: " + testMsg[i].type +" name: "+ testMsg[i].name + " value: " + testMsg[i].value + "<br/>";
                div2.style = "display:none; margin: auto; margin-bottom: 5px; width: 95%; font-size: 1.3em; border-radius: 5px; text-align: center;";
                div2.style.background = this.randomColor();
                div.appendChild(div2);
                break;
              case "NMStack":break;
              case "PCRegister":break;
              default: console.log("ERROR!!!");break;
            }
          }else{
            console.log("NULL!!!");
          }
        },
      },
      watch: {
        mode(val) {
          //监听mode，如果改变了输入的代码种类，则调用changeMode函数
          this.changeMode(val);
        }
      },
    }
</script>

<style scoped>

  #showPage{
    height: 100%;
    background-color: #1e1f26;
  }

  #inputCodeBox{
    height: 100%;
    width: 30%;
    float: left;
  }

  .inputHeader{
    height: 5%;
    background-color: #1a1b1f;
    color: #aaaebc;
    font-size: 1.3em;
    border-bottom: 1px solid #78787840;
    padding-left: 10px;
    padding-right: 10px;
  }

  .inputCode{
    height: 100%;
  }

  .editor_control_2{
    height: 100%;
  }

  /deep/ .CodeMirror{
    height: 100%;
    width: 100%;
    background: #1a1b1f;
    font-size: large;
    font-family: auto;
  }

  /deep/ .CodeMirror-gutters{
    background: #1a1b1f;
  }


  #loadingArea{
    height: 100%;
    border: 18px solid #333642;
    border-top: 0px;
    display: flex;
    flex-direction: row;
  }

  .left2{
    flex: 1;
    height: 100%;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    border-right: 18px solid #333642;
  }

  .right3{
    flex: 1;
    height: 100%;
    -webkit-box-orient: vertical;
    display: -webkit-box;
  }

  .VMStack{
    -webkit-box-flex: 1;
  }

  .methodArea{
    -webkit-box-flex: 1;
  }

  .heap{
    -webkit-box-flex: 1;
  }

  .NMStack{
    -webkit-box-flex: 1;
  }

  .PCRegister{
    -webkit-box-flex: 1;
  }

  .methodHeaderLeft{
    height: 10%;
    background-color: #1a1b1f;
    color: #aaaebc;
    font-size: 1.3em;
    border-bottom: 1px solid #78787840;
    padding-left: 10px;
    padding-right: 10px;
  }

  .methodHeaderRight{
    height: 15%;
    background-color: #1a1b1f;
    color: #aaaebc;
    font-size: 1.3em;
    border-bottom: 1px solid #78787840;
    padding-left: 10px;
    padding-right: 10px;
  }

  .methodName{
    position: relative;
    /*display: block;*/
    top: 18%;
    transform: translateY(-50%);
  }

  .buttonShow{
    border-radius: 5px;
    float: right;
    font-size: 0.8em;
    color: white;
    background-color: #444857;
    cursor:pointer;
    border: 0;
    transform: translateY(52%);
  }

  .methodBody{
    height: 90%;
    color: #fff;
    font-size: 0.9em;
    /*overflow-y:scroll;*/
  }

  #methodAreaData{
    padding-left: 30px;
    width: 85%;
  }

  .zUIpanelScrollBox, .zUIpanelScrollBar {
    width: 10px;
    top: 4px;
    right: 2px;
    border-radius: 5px;

  }

  .zUIpanelScrollBox {
    background: black;
    opacity: 0.1;
    filter: alpha(opacity=10);
  }

  .zUIpanelScrollBar {
    background: #fff;
    opacity: 0.8;
    filter: alpha(opacity=80);
  }

  #demo0{
    height: 90%;
    border: 2px solid #1F1F1F;
    margin: 20px auto;
    overflow: hidden;
  }
  #demo1 {
    /*width: 50%;*/
    height: 99%;
    border: 2px solid #1F1F1F;
    overflow: hidden;
  }
  #demo2{
    height: 90%;
    border: 2px solid #1F1F1F;
    margin: 20px auto;
    overflow: hidden;
  }


</style>
