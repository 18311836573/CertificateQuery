$(function () {
    $("#toolbar_add").click(function () {
        $.ajax({
            type:"post",
            url:"uploadone",
            dataType:"json",
            data:$("#data_add").serialize(),
            success:function (data) {
                alert("增加信息成功！")
                $("#table").bootstrapTable('insertRow',{
                    index:1,
                    row:{
                        number:data.number,
                        area:$("#add_area").val(),
                        workName:$("#add_workName").val(),
                        level:$("#add_level").val(),
                        student:$("#add_name").val(),
                        school:$("#add_school").val(),
                        teacher:$("#add_teacher").val()
                    }
                })
            }
        })
    })

    $("#btn_delete").click(function () {
        $.map($("#table").bootstrapTable('getSelections'), function (row) {
            $.ajax({
                type:"post",
                data:{
                	number : row.number
                	},
                url:"delete",
                success:function (data) {
                    alert("删除成功")
                    $("#table").bootstrapTable('remove', {
                        field: 'number',
                        values: row.number
                    });
                }
            })
            return row.number;
        });
    });

    $("#send").click(function () {
        $.map($("#email_table").bootstrapTable('getSelections'),function (row) {
            $.ajax({
                type:"post",
                dataType:"json",
                url:"download",
                data: row.number,
                success:function () {
                    $("#email").modal('hide')
                }
            })

            return false;
        })
    })
})
