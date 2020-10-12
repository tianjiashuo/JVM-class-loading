function getMethodarea() {
    //设置一个变量用于接收ajax返回的值
    var classname = '';
    var method = '';

    // $.ajax({
    //     url : "http://localhost:8080/methodarea",
    //     type : "get",
    //     async : false,
    //     dataType : "json",
    //     success : function(result) {
    //         console.log(result)
    //         classname = result.classname;
    //         method = result.method;
    //         $('#classname').html(classname);
    //         $('#method').html(method);
    //     },
    //     error : function(e){
    //         console.log(e.status);
    //         console.log(e.responseText);
    //     }
    // });

    $.ajax({
        url : "http://localhost:8080/methodTest",
        type : "get",
        async : false,
        dataType : "json",
        
        success : function(result) {
            console.log("!!!!!!!!!!!!!!!!!!!")
            console.log(result[0]);
            classname = result[1];
            method = result[2];
            $('#classname').html(classname);
            $('#method').html(method);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });

    setTimeout(function () {
        $('.box5').css('display', 'block');//1出现
    }, 500);
    setTimeout(function () {
        $('.box5').css('margin-top', '180%');//1下降
    }, 2000);
}

function getHeap() {
    //设置一个变量用于接收ajax返回的值
    var name = '';
    var value = '';
    var quote = '';
    $.ajax({
        url : "http://localhost:8080/heap",
        type : "get",
        async : false,
        dataType : "json",
        success : function(result) {
            console.log(result)
            name = result.name;
            value = result.value;
            quote = result.quote;
            $('#name').html(name);
            $('#value').html(value)
            $('#quote').html(quote);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });


    setTimeout(function () {
        $('.box4').css('display', 'block');//1出现
    }, 500);
    setTimeout(function () {
        $('.box4').css('margin-top', '180%');//1下降
    }, 2000);
}

function getStack() {
    //设置一个变量用于接收ajax返回的值
    var variableName = '';
    var type = '';
    var value = '';
    var quote = '';

    $.ajax({
        url : "http://localhost:8080/stack",
        type : "get",
        async : false,
        dataType : "json",
        success : function(result) {
            console.log(result)
            variableName = result.variableName;
            type = result.type;
            value = result.value;
            quote = result.quote;
            $('#variableName').html(variableName);
            $('#type').html(type);
            $('#valueStack').html(value);
            $('#quoteStack').html(quote);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    setTimeout(function () {
        $('.box1').css('display', 'block');//1出现
    }, 500);
    setTimeout(function () {
        $('.box1').css('margin-top', '180%');//1下降
    }, 2000);
    setTimeout(function () {
        $('.box2').css('display', 'block');//堆2出现
    }, 2500);
    setTimeout(function () {
        $('.box2').css('margin-top', '155%');
    }, 4000);
    setTimeout(function () {
        $('.box3').css('display', 'block');//堆3出现
    }, 4500);
    setTimeout(function () {
        $('.box3').css('margin-top', '130%');
    }, 6000);

}