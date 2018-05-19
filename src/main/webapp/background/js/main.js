/**
 * Created by Ma on 2016/11/25.
 */

/**
 * 2秒后自动关闭提示信息
 */
$(document).ready(function() {
	setTimeout("$('.alert-mask').fadeTo(300,0).slideUp();",2000); 
});

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function warning(text,lost_time){
    var alert_html = '<div class="alert-mask"><div class="alert-content">' +
            '<p>'+text+'</p>'+
            '</div></div>';
    $('body').append(alert_html);
    setTimeout(function () {
        $('.alert-mask').fadeTo(300,0).slideUp()
    },lost_time)
}