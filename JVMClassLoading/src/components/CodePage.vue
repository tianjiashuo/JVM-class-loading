<template>
  <div id="codePage">
    <Header/>
    <!--Select选择器下拉框样式无法修改原因
    通过Dom元素可以发现，官方Select选择器下拉框是直接插入body标签中，与最外级div同级，所以在Vue中无论怎么写css都无法覆盖到下拉框样式。
    :popper-append-to-body（默认值是 true）
    在Select选择器上，添加 :popper-append-to-body=“false”
    从Dom元素中可以看出，这次下拉框元素被直接插入到 el-select 标签内，可以编写css去覆盖样式了-->
    <el-select class="code-mode-select" v-model="mode" :popper-append-to-body="false" @change="changeMode">
      <el-option v-for="mode in modes" :key="mode.value" :label="mode.label" :value="mode.value">
      </el-option>
    </el-select>

    <div class="code">
      <div id="files_tree"></div>
      <textarea id="codeInputBox" ref="textarea"></textarea>
    </div>

    <div class="terminal">
      <div class="buttons">
        <img id="run" src="../assets/run.png" @click="run"/>
        <img id="stop" src="../assets/stop.png"/>
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
      name: "CodePage",
      components: {
        Header,
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
        // 初始化
        this._initialize()
      },
      methods: {
        // 初始化
        _initialize () {
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
        _getLanguage (language) {
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
        changeMode (val) {
          console.log("wobianlalaala " + val);
          // 修改编辑器的语法配置
          this.coder.setOption('mode', `text/${val}`);
          // 获取修改后的语法
          let label = this._getLanguage(val).label.toLowerCase();
          // 允许父容器通过以下函数监听当前的语法值
          this.$emit('language-change', label)
        },
        run(){
          let that = this;
          that.$router.push({
            name: 'ShowPage',
            params: {
              code: this.code,
              mode:this.mode,
            }
          });

          // 将代码传给后端
          var test = this.code;
          var aniTime = 500;

          // $.ajax({
          //     type : "post",
          //     url : "http://localhost:8080/code/receive",//servlet
          //     contentType:"application/json",
          //     data : {"code":test },
          //     success : function(msg){
          //         var testMsg = JSON.stringify(msg);
          //         testMsgLen = getJsonLength(msg);
          //         console.log("99999999999999 "+ testMsgLen);
          //         console.log("~~~~~~~~~~~ "+ msg.data.data);
          //         setTimeout(function () {
          //             for (var i = 1; i <= testMsgLen; i++) {
          //                 showData(i,testMsg);
          //                 // console.log("延迟开始");
          //                 $("#"+i).delay(aniTime).fadeIn();
          //                 // console.log("延迟结束");
          //                 aniTime += 500;
          //             }
          //         }, 100);
          //     },
          //     error : function(){
          //         alert("error");
          //     },
          // });

          // setTimeout(function () {
          //   for (var i = 1; i <= testMsgLen; i++) {
          //     showData(i,testMsg);
          //     // console.log("延迟开始");
          //     $("#"+i).delay(aniTime).fadeIn();
          //     // console.log("延迟结束");
          //     aniTime += 300;
          //   }
          // }, 100);

        },

      },
    }
</script>

<style scoped>

  #codePage{
    /*display: none;*/
    height: 100%;
    margin: 0;
    overflow: hidden;
    background-color: #1e1f26;
  }

  #files_tree{
    height:100% ;
    width:15%;
    background: #3C3F41;
    border: 3px solid #313335;
    border-radius: 6px;
    margin-right: 5px;
  }

  .code{
    display: flex;
    height: 70%;
    width: 98%;
    margin: 0 auto;
    padding-top: 5px;
  }

  /*使用 /deep/ 操作符穿透scoped,更改第三方组件的样式*/
  /*更改代码框的样式*/
  /deep/ .CodeMirror{
    height: 100%;
    width: 100%;
    background: #1a1b1f;
    font-size: large;
    font-family: auto;
    border: 3px solid #313335;
    border-radius: 6px;
  }

  /*更改代码框左边行数的样式*/
  /deep/ .CodeMirror-gutters{
    background: #1a1b1f;
  }

  /*更改选择框的样式*/
  /deep/ .el-select{
    position: absolute;
    z-index: 2;
    right: 1%;
    /*width: 121px;*/
    top:1%;
  }

  /deep/ .el-input__inner{
    background: #1e1f26;
    color: #fff;
    border-color: #f2f6fc;
  }

  /deep/ .el-select .el-input__inner:focus{
    border-color: #f2f6fc;
  }

  /deep/ .el-select-dropdown{
    background-color: #1e1f26;
  }

  /deep/ .el-select-dropdown__item{
    color: #ccc;
  }

  /deep/ .el-select-dropdown__item:hover{
    background: #444857;
  }

  /deep/ .el-select-dropdown__item.selected{
    color: #fff;
    background: #444857;
  }


  .terminal{
    height: 19%;
    width: 98%;
    margin: 0 auto;
    background: #1a1b1f;
    margin-top: 10px;
    border: 3px solid #313335;
    border-radius: 6px;
  }

  .buttons{
    width: 20px;
    background: #313335;
    height: 100%;
    border-radius: 4px;
    cursor: pointer;
  }
  #run{
    height: 15px;
    margin-left: 3px;
    margin-top: 10px;
  }
  #stop{
    height: 12px;
    margin-left: 5px;
    margin-top: 8px;
  }


</style>



<!--<template>-->
<!--  <div id="codePage">-->
<!--    <Header/>-->
<!--    <div class="code">-->
<!--      <div id="files_tree">-->

<!--      </div>-->
<!--&lt;!&ndash;      <div class="editor_control">&ndash;&gt;-->
<!--&lt;!&ndash;				<textarea id="code" class="form-control" value="code" name="code" rows="12">&ndash;&gt;-->

<!--&lt;!&ndash;				</textarea>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--      <div class="codemirror">-->
<!--        <codemirror id="inputCode" ref="myCm"-->
<!--                    v-model="formData.queryText"-->
<!--                    :options="cmOptions"-->
<!--                    @ready="onCmReady"-->
<!--                    @focus="onCmFocus"-->
<!--                    @input="onCmCodeChange"-->
<!--        style="background: #9f8198">-->
<!--        </codemirror>-->
<!--      </div>-->
<!--    </div>-->

<!--    <div class="terminal">-->
<!--      <div class="buttons">-->
<!--        <img id="run" src="../assets/run.png" @click="run"/>-->
<!--        <img id="stop" src="../assets/stop.png"/>-->
<!--      </div>-->
<!--    </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import Header from "./Header";-->

<!--  import 'codemirror/mode/sql/sql.js'-->
<!--  // theme css-->
<!--  import 'codemirror/theme/solarized.css'-->
<!--  // require active-line.js-->
<!--  import 'codemirror/addon/selection/active-line.js'-->
<!--  // closebrackets-->
<!--  import 'codemirror/addon/edit/closebrackets.js'-->
<!--  // keyMap-->
<!--  import 'codemirror/mode/clike/clike.js'-->
<!--  import 'codemirror/addon/edit/matchbrackets.js'-->
<!--  import 'codemirror/addon/comment/comment.js'-->
<!--  import 'codemirror/addon/dialog/dialog.js'-->
<!--  import 'codemirror/addon/dialog/dialog.css'-->
<!--  import 'codemirror/addon/search/searchcursor.js'-->
<!--  import 'codemirror/addon/search/search.js'-->
<!--  import 'codemirror/keymap/emacs.js'-->

<!--  //自动提示-->
<!--  import 'codemirror/addon/hint/show-hint.css';-->
<!--  import 'codemirror/addon/hint/show-hint.js';-->
<!--  import 'codemirror/addon/hint/sql-hint.js';-->

<!--  export default {-->
<!--    name: "CodePage",-->
<!--    components: {-->
<!--      Header-->
<!--    },-->
<!--    data(){-->
<!--      return{-->
<!--        testMsg:{-->
<!--          "1": null,-->
<!--          "2": null,-->
<!--          "3": {-->
<!--            "area": "MethodArea",-->
<!--            "type": null,-->
<!--            "classname": "com/jvm/demo/source/Add",-->
<!--            "pool": [-->
<!--              0,-->
<!--              1-->
<!--            ],-->
<!--            "method": [-->
<!--              "addcd()",-->
<!--              "add()"-->
<!--            ],-->
<!--            "param": null,-->
<!--            "value": null,-->
<!--            "fieldName": null-->
<!--          },-->
<!--          "4": {-->
<!--            "area": "MethodArea",-->
<!--            "type": "int",-->
<!--            "classname": null,-->
<!--            "pool": null,-->
<!--            "method": null,-->
<!--            "param": null,-->
<!--            "value": "0",-->
<!--            "fieldName": "a"-->
<!--          },-->
<!--          "5": {-->
<!--            "area": "MethodArea",-->
<!--            "type": "int",-->
<!--            "classname": null,-->
<!--            "pool": null,-->
<!--            "method": null,-->
<!--            "param": null,-->
<!--            "value": "1",-->
<!--            "fieldName": "b"-->
<!--          },-->
<!--          "6": null,-->
<!--          "7": {-->
<!--            "area": "Heap",-->
<!--            "name": "c",-->
<!--            "value": "3",-->
<!--            "type": "int"-->
<!--          },-->
<!--          "8": {-->
<!--            "area": "Heap",-->
<!--            "name": "d",-->
<!--            "value": "4",-->
<!--            "type": "int"-->
<!--          },-->
<!--          "9": null,-->
<!--          "10": null,-->
<!--          "11": null,-->
<!--          "12": null,-->
<!--          "13": null,-->
<!--          "14": null,-->
<!--          "15": null,-->
<!--          "16": null,-->
<!--          "17": null,-->
<!--          "18": {-->
<!--            "area": "MethodArea",-->
<!--            "type": "void",-->
<!--            "classname": null,-->
<!--            "pool": null,-->
<!--            "method": [-->
<!--              "main"-->
<!--            ],-->
<!--            "param": [-->
<!--              "String[] args"-->
<!--            ],-->
<!--            "value": null,-->
<!--            "fieldName": null-->
<!--          },-->
<!--          "19": {-->
<!--            "area": "JvmStack",-->
<!--            "referenceName": null,-->
<!--            "type": null,-->
<!--            "methodName": "add",-->
<!--            "args": null-->
<!--          },-->
<!--          "20": {-->
<!--            "area": "JvmStack",-->
<!--            "referenceName": "add",-->
<!--            "type": "Add",-->
<!--            "methodName": null,-->
<!--            "args": null-->
<!--          },-->
<!--          "21": {-->
<!--            "area": "Heap",-->
<!--            "name": "add",-->
<!--            "value": null,-->
<!--            "type": "Add"-->
<!--          },-->
<!--          "22": {-->
<!--            "area": "JvmStack",-->
<!--            "referenceName": null,-->
<!--            "type": "int",-->
<!--            "methodName": "addcd",-->
<!--            "args": null-->
<!--          },-->
<!--          "23": null-->
<!--        },-->
<!--        testMsgLen:23,-->

<!--        formData:{-->
<!--          queryText:''-->
<!--        },-->
<!--        cmOptions: {-->
<!--          tabSize: 4,-->
<!--          styleActiveLine: true,-->
<!--          lineNumbers: true,-->
<!--          line: true,-->
<!--          mode: 'text/x-mysql',-->

<!--          extraKeys: {"Ctrl": "autocomplete"},   //自动提示配置-->

<!--          lineWrapping: true,    // 自动换行-->
<!--          theme: 'solarized light'-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    methods:{-->
<!--      // Init(){-->
<!--      //   var editor = CodeMirror.fromTextArea(document.getElementById("code"), {-->
<!--      //     lineNumbers: true,-->
<!--      //     matchBrackets: true,-->
<!--      //     mode: "text/x-java",-->
<!--      //     indentUnit: 4,-->
<!--      //     indentWithTabs: true,-->
<!--      //   });-->
<!--      // },-->

<!--      run(){-->
<!--        var temp = editor.getValue();-->
<!--        console.log(temp);-->
<!--        editor1.setValue(temp);-->

<!--        $('#showPage').css('display', 'block');-->
<!--        $('#inputCodePage').css('display', 'none');-->

<!--        // 将代码传给后端-->
<!--        var test = $('#code').val();-->
<!--        console.log(test);-->
<!--        // var testMsgLen = 0;-->
<!--        var aniTime = 500;-->

<!--        // $.ajax({-->
<!--        //     type : "post",-->
<!--        //     url : "http://localhost:8080/code/receive",//servlet-->
<!--        //     contentType:"application/json",-->
<!--        //     data : {"code":test },-->
<!--        //     success : function(msg){-->
<!--        //         var testMsg = JSON.stringify(msg);-->
<!--        //         testMsgLen = getJsonLength(msg);-->
<!--        //         console.log("99999999999999 "+ testMsgLen);-->
<!--        //         console.log("~~~~~~~~~~~ "+ msg.data.data);-->
<!--        //         setTimeout(function () {-->
<!--        //             for (var i = 1; i <= testMsgLen; i++) {-->
<!--        //                 showData(i,testMsg);-->
<!--        //                 // console.log("延迟开始");-->
<!--        //                 $("#"+i).delay(aniTime).fadeIn();-->
<!--        //                 // console.log("延迟结束");-->
<!--        //                 aniTime += 500;-->
<!--        //             }-->
<!--        //         }, 100);-->
<!--        //     },-->
<!--        //     error : function(){-->
<!--        //         alert("error");-->
<!--        //     },-->
<!--        // });-->

<!--        setTimeout(function () {-->
<!--          for (var i = 1; i <= this.testMsgLen; i++) {-->
<!--            showData(i,this.testMsg);-->
<!--            // console.log("延迟开始");-->
<!--            $("#"+i).delay(aniTime).fadeIn();-->
<!--            // console.log("延迟结束");-->
<!--            aniTime += 300;-->
<!--          }-->
<!--        }, 100);-->
<!--      },-->

<!--      showSQL(val){-->
<!--        this.formData.queryText = val?val:''-->
<!--      },-->
<!--      onCmReady(cm) {-->
<!--        this.codemirror.setSize("-webkit-fill-available", "auto");-->
<!--        // this.codemirror.setOptions("theme","ambiance")-->
<!--      },-->
<!--      onCmFocus(cm) {-->
<!--        // console.log('the editor is focus!', cm)-->
<!--      },-->
<!--      onCmCodeChange(newCode) {-->
<!--        console.log('this is new code', newCode)-->
<!--        this.formData.queryText = newCode-->
<!--        this.$emit('codeChange',this.formData.queryText)-->
<!--      }-->
<!--    },-->
<!--    computed:{-->
<!--      codemirror() {-->
<!--        return this.$refs.myCm.codemirror-->
<!--      }-->
<!--    },-->
<!--    created() {-->
<!--      // this.Init();-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->

<!--  #codePage{-->
<!--    /*display: none;*/-->
<!--    height: 100%;-->
<!--    margin: 0;-->
<!--    overflow: hidden;-->
<!--    background-color: #1e1f26;-->
<!--  }-->

<!--  #files_tree{-->
<!--    height:100% ;-->
<!--    width:15%;-->
<!--    background: #3C3F41;-->
<!--    border: 3px solid #313335;-->
<!--    border-radius: 6px;-->
<!--    margin-right: 5px;-->
<!--  }-->

<!--  .code{-->
<!--    display: flex;-->
<!--    height: 70%;-->
<!--    width: 98%;-->
<!--    margin: 0 auto;-->
<!--    padding-top: 5px;-->
<!--  }-->

<!--  .terminal{-->
<!--    height: 19%;-->
<!--    width: 98%;-->
<!--    margin: 0 auto;-->
<!--    background: #1a1b1f;-->
<!--    margin-top: 10px;-->
<!--    border: 3px solid #313335;-->
<!--    border-radius: 6px;-->
<!--  }-->

<!--  .buttons{-->
<!--    width: 20px;-->
<!--    background: #313335;-->
<!--    height: 100%;-->
<!--    border-radius: 4px;-->
<!--    cursor: pointer;-->
<!--  }-->
<!--  #run{-->
<!--    height: 15px;-->
<!--    margin-left: 3px;-->
<!--    margin-top: 10px;-->
<!--  }-->
<!--  #stop{-->
<!--    height: 12px;-->
<!--    margin-left: 5px;-->
<!--    margin-top: 8px;-->
<!--  }-->

<!--  .codemirror {-->
<!--    width: 85%;-->
<!--    background: #1a1b1f;-->
<!--  }-->
<!--  .codemirror >>> .CodeMirror-scroll {-->
<!--    min-height: 180px;-->
<!--  }-->

<!--  #inputCode {-->
<!--    height: 100%;-->
<!--  }-->

<!--</style>-->
