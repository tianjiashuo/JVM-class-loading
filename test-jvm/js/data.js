//得到json的长度
function getJsonLength(json){
    var jsonLength = 0;
    for (var i in json) {
        jsonLength++;
    }
    return jsonLength;
}

function getMethodarea() {
    var str = "";

    $.ajax({
        url : "http://47.94.170.167:8081/methodTest",
        type : "get",
        async : false,
        dataType : "json",
        success : function(result) {
            var jsonLen = getJsonLength(result);
            console.log(jsonLen);
            for(var i=0;i<jsonLen;i++){
                str += "<li>" + result[i]+ "</li>";
            }
            document.getElementById("methodAreaData").innerHTML = str;
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

startClick = function () {
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
    // setTimeout(function () {
    //     $(".startPage").addClass("startPageDisapper");
    // }, 1200);
    setTimeout(function () {
        $('#mainPage').css('display', 'block');
        $('#inputCodePage').css('display', 'block');
        $('#startPage').css('display', 'none');
    }, 1950);
}

//随机生成div的颜色
function randomColor(){
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
}

//显示数据
function showData(i,testMsg){
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
                div2.style.background = randomColor();
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
                div2.style.background = randomColor();
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
                div2.style.background = randomColor();
                div.appendChild(div2);
                break;
            case "NMStack":break;
            case "PCRegister":break;
            default: console.log("ERROR!!!");break;
        }
    }else{
        console.log("NULL!!!");
    }
}

run = function () {
    var temp = editor.getValue();
    console.log(temp);
    editor1.setValue(temp);

    $('#showPage').css('display', 'block');
    $('#inputCodePage').css('display', 'none');

    // 将代码传给后端
    var test = $('#code').val();
    console.log(test);
    // var testMsgLen = 0;
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

    var testMsg = {
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
    }
    var testMsgLen = 23;

    // setTimeout(function () {
    //                 for (var i = 1; i <= testMsgLen; i++) {
    //                     showData(i,testMsg);
    //                     // console.log("延迟开始");
    //                     $("#"+i).delay(aniTime).fadeIn();
    //                     // console.log("延迟结束");
    //                     aniTime += 300;
    //                 }
    //             }, 100);
    for (var i = 1; i <= testMsgLen; i++) {
        showData(i,testMsg);
        // console.log("延迟开始");
        $("#"+i).delay(aniTime).fadeIn();
        // console.log("延迟结束");
        aniTime += 300;
    }

}

checkTxt = function () {
    console.log("改变了");
    console.log( $('#code').value);
}


window.onload = function(){

    $('#code').bind('input propertychange', function() {
        console.info('值在改变:'+$(this).val());})


        var el = document.getElementById('code');
    el.addEventListener('input',function () {
        console.log("改变了");
        console.log(el.value);
    });


//     for(var i=1;i<=3;i++){
//         var randomNum = parseInt(Math.ceil(Math.random()*4));
//         console.log(randomNum);
//         var color = '';
//         var maName = 'maDemoData';
//         maName = maName + i;
//         console.log(maName);
//         switch(randomNum) {
//             case 0:
//                 color = '#de7300';
//                 break;
//             case 1:
//                 color = '#7a99ad';
//                 break;
//             case 2:
//                 color = '#e1ca72';
//                 break;
//             case 3:
//                 color = '#9f8198';
//                 break;
//             default:
//                 color = '#ffffff';
//         }
//         $(maName).css('color', color);
//         console.log("~~~~~~~");
//     }
}


