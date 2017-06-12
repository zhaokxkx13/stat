/**
 * Created by zhaokxkx13 on 2017/5/31.
 */
function repaint() {
    var name = $("#select_k3").val();
    $("#select_k3").show(this.value);
    $.ajax({
        type: "get",
        dataType: 'text',
        cache: false,
        url: "/permission/userRoleInfo",
        data: "username=" + name,
        success: function (data) {
            var str = data;
            var json = JSON.parse(data);
            var userId = json['userId'];
            json = json['roleList'];
            $("#userRoles tr:not(:first):not(:last)").remove();
            // for(var item in  json){
            //     $("<tr><td>item['name']</td><td>item['description']</td><td>插入</td></tr>").insertAfter($("#userRoles tr:eq(1)"));
            // }
            for (var i = 0; i < json.length; i++) {
                $("<tr><td>" + json[i]['name'] + "</td><td>" + json[i]['description']
                    + "</td><td><a href='/permission/userRole/delete?userId=" + userId + "&roleId=" + json[i]['id'] + "' class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a></td></tr>").insertAfter($("#userRoles tr:eq(0)"));
            }
        }
    })


    $.ajax({
        type: "get",
        dataType: 'text',
        cache: false,
        url: "/permission/userRoleUnauthInfo",
        data: "username=" + name,
        success: function (data) {
            var str = data;
            var json = JSON.parse(data);
            var userId = json['userId'];
            json = json['roleList'];
            $("#userRolesAdd tr:not(:first):not(:last)").remove();
            // for(var item in  json){
            //     $("<tr><td>item['name']</td><td>item['description']</td><td>插入</td></tr>").insertAfter($("#userRoles tr:eq(1)"));
            // }
            for (var i = 0; i < json.length; i++) {
                $("<tr><td>" + json[i]['name'] + "</td><td>" + json[i]['description']
                    + "</td><td><a href='/permission/userRole/add?userId=" + userId + "&roleId=" + json[i]['id'] + "' class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 添加 </a></td></tr>").insertAfter($("#userRolesAdd tr:eq(0)"));
            }
        }
    })
}