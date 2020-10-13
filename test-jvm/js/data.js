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
        switch(testMsg[i].area){
            case "JvmStack":
                //获取div
                var div = document.getElementById("vmStackBody");
                //添加子div
                var div2 = document.createElement("div");
                div2.id = i;
                div2.innerHTML = testMsg[i].type  +" "+ testMsg[i].methodName +" "+testMsg[i].method + " "+testMsg[i].fieldName + " "+testMsg[i].value +"<br/>";
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
                div2.innerHTML = testMsg[i].type  +" "+ testMsg[i].classname +" "+testMsg[i].args + "<br/>";
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
                div2.innerHTML = testMsg[i].type  +" "+ testMsg[i].name + " = " + testMsg[i].value + "<br/>";
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
    console.log("!!!!!!");

    // 将代码传给后端
    // var test = $('#code').val();
    // // var test = document.getElementById("code").value;
    // // var test = "int i = 0";
    // console.log(test);
    //
    // $.ajax({
    //     type : "post",
    //     url : "http://localhost:8080/aaa",//servlet
    //     contentType:"application/json",
    //     data : {"code":test },
    //     success : function(msg){
    //         console.log(msg);
    //         alert(msg);
    //     },
    //     error : function(){
    //         alert("error");
    //     },
    // });

    $('#showPage').css('display', 'block');
    $('#inputCodePage').css('display', 'none');

    var testMsg = {
        "1": null,
        "2": {
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
        "3": {
            "area": "MethodArea",
            "type": "int",
            "classname": null,
            "pool": null,
            "method": null,
            "param": null,
            "value": "1",
            "fieldName": "b"
        },
        "4": null,
        "5": {
            "area": "Heap",
            "name": "c",
            "value": "3",
            "type": "int"
        },
        "6": {
            "area": "JvmStack",
            "referenceName": null,
            "type": null,
            "methodName": "add",
            "args": null
        }
    }
    var testMsgLen = 6;
    var aniTime = 500;

    setTimeout(function () {
        for (var i = 1; i <= testMsgLen; i++) {
            showData(i,testMsg);
            // console.log("延迟开始");
            $("#"+i).delay(aniTime).fadeIn();
            // console.log("延迟结束");
            aniTime += 500;
        }
    }, 100);

}


// window.onload = function(){
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
// }


