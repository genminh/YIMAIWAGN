function login(){
    var loginName = jQuery("#loginName").val();
    var password = jQuery("#password").val();
    jQuery.ajax({
        url:"Login",
        type:"post",
        data:{loginName:loginName,password:password,action:"login"},
        dataType:"json",
        success:function(jsonStr){
            if(jsonStr.status==1){
                window.location.href="/Home?action=index";
            }else{
                showMessage(jsonStr.message);
            }
        },
        error:function () {
            alert("失败了！")
        }
    });
}