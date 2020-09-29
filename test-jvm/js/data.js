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
    setTimeout(function () {
        $(".startPage").addClass("startPageDisapper");
    }, 1200);
    // setTimeout(function () {
    //     $('#mainPage').css('display', 'block');
    //     $('#startPage').css('display', 'none');
    // }, 1950);
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


