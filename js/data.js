function getMethodarea() {
    //设置一个变量用于接收ajax返回的值
    var classname = '';
    var method = '';

    $.ajax({
        url : "http://localhost:8080/methodarea",
        type : "get",
        async : false,
        dataType : "json",
        success : function(result) {
            console.log(result)
            classname = result.classname;
            method = result.method;
            $('#classname').html(classname);
            $('#method').html(method);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
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
            $('#name').html(name);
            $('#value').html(value)
            $('#quote').html(quote);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
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
            $('#value').html(value);
            $('#quote').html(quote);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}