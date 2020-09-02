let p=[0,0,0,0,0,0,0];
let roll=null;


document.onreadystatechange = function () {
    console.log(document.readyState);
    if (document.readyState === "complete") {
        $(".loading").fadeOut();
        setTimeout(function () {
            $('.box1').css('display', 'block');//堆1出现
        }, 500);
        setTimeout(function () {
            $('.box1').css('margin-top', '-30%');
        }, 2000);
        setTimeout(function () {
            $('.box2').css('display', 'block');//堆2出现
        }, 2500);
        setTimeout(function () {
            $('.box2').css('margin-top', '-35%');
        }, 4000);
        setTimeout(function () {
            $('.box3').css('display', 'block');//堆3出现
        }, 4500);
        setTimeout(function () {
            $('.box3').css('margin-top', '-35%');
        }, 6000);
        setTimeout(function () {
            $('.box4').css('display', 'block');//堆3出现
        }, 6500);
        setTimeout(function () {
            $('.box4').css('margin-top', '-30%');
        }, 8000);
        setTimeout(function () {
            $('.box5').css('display', 'block');//堆3出现
        }, 8500);
        setTimeout(function () {
            $('.box5').css('margin-top', '-30%');
        }, 10000);
        setTimeout(function () {
            $('.box3').css('margin-top', '-115%');
        }, 10500);
        setTimeout(function () {
            $('.box3').css('display', 'none');
        }, 11000);
        setTimeout(function () {
            $('.box5').css('margin-top', '-150%');
        }, 11500);
        setTimeout(function () {
            $('.box5').css('display', 'none');
        }, 12300);
        // setTimeout(function () {
        //     $('.box3').css('margin-top', '-115%');//堆3出现
        // }, 10500);
        // setTimeout(function () {
        //     $('.box3').css('display', 'none');
        // }, 12000);
    //     setTimeout(function () {
    //         $('.sentence1').css('display', 'block');//TWT STUDIO出现
    //     }, 500);
    //     setTimeout(function () {
    //         $('.sentence2').css('display', 'block');//WE WANT YOU出现
    //     }, 1500);
    //     setTimeout(function () {
    //         $('.logo2').show();//第二朵云浮现
    //     }, 1800);
    //     setTimeout(function () {
    //         $('.sentence1').css('visibility', 'hidden');
    //     }, 2550);
    //     setTimeout(function () {
    //         $('.sentence2').css('margin-top', '-22px');
    //     }, 2600);
    //     setTimeout(function () {
    //         $('.sentence3').css('display', 'block');
    //         $('.arrow').css('display', 'block');
    //     }, 3100);
    //     $("#p1-1").css('display','block');
    }
}


$(document).ready(function(){//激活fullpage
    $('#fullpage').fullpage({
        afterLoad:function(anchorLink,index) {//滚动到某一屏后的回调函数
            if (p[index - 1] === 0) {
                p[index - 1] = 1;
                switch (index) {
                    case 1:
                            $(function () {
                                /*setTimeout(function () {
                                    $('.sentence1').css('display', 'block');//TWT STUDIO出现
                                }, 500);
                                setTimeout(function () {
                                    $('.sentence2').css('display', 'block');//WE WANT YOU出现
                                }, 1500);
                                setTimeout(function () {
                                    $('.logo2').show();//第二朵云浮现
                                }, 1800);
                                setTimeout(function () {
                                    $('.sentence1').css('visibility', 'hidden');
                                }, 2550);
                                setTimeout(function () {
                                    $('.sentence2').css('margin-top', '-22px');
                                }, 2600);
                                setTimeout(function () {
                                    $('.sentence3').css('display', 'block');
                                    $('.arrow').css('display', 'block');
                                }, 3100);*/
                            })
                        break;
                }
            }
        },
        onLeave: function (index,nextIndex,direction) {
            if(index===1 & nextIndex ===2){
            }
            if(index){
                $('.arrow').css('display','none');
            }
        },
    })
})
function hahahaclick() {
    $.fn.fullpage.moveSectionDown();
}
function init() {
    let left = document.getElementsByClassName('left')[0];
    let right = document.getElementsByClassName('right')[0];
    let a=0
    let value = 0;
    let timer = setInterval(() => {;
        if (a < 100) {
            a = value++;
            if (value <= 50) {
                right.style.transform = 'rotate(' + (value * 3.6) + 'deg)';
            } else if (value <= 100) {
                left.style.transform = 'rotate(' + ((value - 50) * 3.6) + 'deg)';
            }
        } else {
            clearInterval(timer);
        }
    }, 25);
}

//
function set() {
    let r = document.getElementsByClassName("reach")
    let h = window.innerHeight
    console.log(h)
    for (let i=0;i<r.length;i++){
        r[i].style.left = "50%";
        if (h<600) {
            r[i].style.bottom = "174.5px";
            r[i].style.marginLeft = "-43.5px";
        }
        else if (h<800) {
            r[i].style.bottom = "222px";
            r[i].style.marginLeft = "-48.5px";
        }
        else if (h<1000){
            r[i].style.bottom = "275px";
            r[i].style.marginLeft = "-61px";
        }
        else if (h<1200){
            r[i].style.bottom = "352px";
            r[i].style.marginLeft = "-80px";
        }
        else {
            r[i].style.bottom = "444px";
            r[i].style.marginLeft = "-97px";
        }
    }
}
