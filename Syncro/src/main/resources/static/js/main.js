/* Top navigation buttons call Controllers mapped to their id */
$(".header_button").click(function() {
	$('.header_button').parent().removeClass('active');
	$(this).parent().toggleClass("active");

	var id = this.id;

	$.post("/" + id, {
		view : id
	}, function(data, status) {
		$("#main_content").hide().html(data).fadeIn('slow');
	});
});

/* Search list */
$(document).ready(function() {
	(function($) {

		$('#filter').keyup(function() {

			var rex = new RegExp($(this).val(), 'i');
			$('.searchable tr').hide();
			$('.searchable tr').filter(function() {
				return rex.test($(this).text());
			}).show();

		})

	}(jQuery));

});

/* Delete entity JS command */

/* Delete user's project */
$("body").on('click', ".delete_user_project_icon", function() {

	var userConfirm = confirm("Are you sure?");

	if (userConfirm === true) {
		$.post("/deleteProjectToUser", {
			id : $('._id').val(),
			projectId : $(this).parent().siblings(".unique_value").html()
		}).done(function(data) {
			$("#user_projects_list").hide().html(data).fadeIn('slow');
		});
	}
});

/* Delete partner's project */
$("body").on('click', ".delete_partner_project_icon", function() {

	var userConfirm = confirm("Are you sure?");

	if (userConfirm === true) {
		$.post("/deleteProjectToPartner", {
			id : $('._id').val(),
			projectId : $(this).parent().siblings(".unique_value").html()
		}).done(function(data) {
			$("#partner_projects_list").hide().html(data).fadeIn('slow');
		});
	}
});

/* Editing users and partners projects/partnerships */
$("body")
		.on(
				'click',
				".edit_user_project_icon",
				function() {

					$(this).hide();
					$(this).siblings(".save_edited_user_project_icon").show();

					var status = $(this).parent().siblings(
							".user_project_status").html();
					var info = $(this).parent().siblings(".user_project_info")
							.html();

					$(this)
							.parent()
							.siblings(".user_project_status")
							.html(
									'<input class="edited_status input-xlarge" name="user_project_status" type="text" placeholder="" value="'
											+ status + '"/>');
					$(this).parent().siblings(".user_project_info").html(
							'<textarea class="edited_info" name="user_project_info" rows="4" cols="50">'
									+ info + '</textarea>');
				});

$("body").on(
		'click',
		".save_edited_user_project_icon",
		function() {

			$(this).siblings(".edit_user_project_icon").show();
			$(this).hide();

			$.post(
					"/editUsersProject",
					{
						id : $('.unique_value').val(),
						projectId : $(this).parent().siblings(".unique_value")
								.html(),
						userProjectStatus : $(this).parent().siblings(
								".user_project_status").find(".edited_status")
								.val(),
						userProjectInfo : $(this).parent().siblings(
								".user_project_info").find(".edited_info")
								.val()
					}).done(function(data) {
				$("#user_projects_list").hide().html(data).fadeIn('slow');
			});
		});

$("body")
		.on(
				'click',
				".edit_partner_project_icon",
				function() {

					$(this).hide();
					$(this).siblings(".save_edited_partner_project_icon")
							.show();

					var status = $(this).parent().siblings(
							".partner_project_status").html();
					var info = $(this).parent().siblings(
							".partner_project_info").html();

					$(this)
							.parent()
							.siblings(".partner_project_status")
							.html(
									'<input class="edited_status input-xlarge" name="partner_project_status" type="text" placeholder="" value="'
											+ status + '"/>');
					$(this).parent().siblings(".partner_project_info").html(
							'<textarea class="edited_info" name="partner_project_info" rows="4" cols="50">'
									+ info + '</textarea>');
				});

$("body").on(
		'click',
		".save_edited_partner_project_icon",
		function() {

			$(this).siblings(".edit_partner_project_icon").show();
			$(this).hide();

			$.post(
					"/editPartnersProject",
					{
						id : $('.unique_value').val(),
						projectId : $(this).parent().siblings(".unique_value")
								.html(),
						partnerProjectStatus : $(this).parent().siblings(
								".partner_project_status").find(
								".edited_status").val(),
						partnerProjectInfo : $(this).parent().siblings(
								".partner_project_info").find(".edited_info")
								.val()
					}).done(function(data) {
				$("#partner_projects_list").hide().html(data).fadeIn('slow');
			});
		});

/* USERS */
$("body").on('click', '#addUserButton', function(event) {
	$.post("/showAddUserForm", {
		view : this.id
	}, function(data, status) {
		$("#main_content").html(data);
	});
});

$("body").on('click', '.user_tbody .edit_icon', function(event) {
	event.stopPropagation();
	var id = $(this).parent().siblings(".unique_value").html()
	$.post("/showEditUserForm", {
		userID : id
	}, function(data, status) {
		var content = '<div id="main_content">' + data + '</div>'
		$("#main_content").html(data);
	});
});

$("body").on('click', '.user_tbody .delete_icon', function(event) {
	event.stopPropagation();
	var userConfirm = confirm("Are you sure?");
	if (userConfirm === true) {
		var id = $(this).parent().siblings(".unique_value").html();
		$.post("/deleteUser", {
			userID : id
		}, function(data, status) {
			var content = '<div id="main_content">' + data + '</div>'
			$("#main_content").html(data);
		});
	}
});

$("body").on('click', '.user_tbody .row_main_info', function(event) {
	event.stopPropagation();
	var id = $(this).children(".unique_value").html();
	var clickedRow = $(this);

	if ($(clickedRow).siblings(".user_extra_info_row").length != 0) {
		$(".user_extra_info_row").replaceWith("");
	} else {
		$.post("/getAdditionalUserInfo", {
			unique_value : id
		}, function(data, status) {
			$(clickedRow).after(data);
		});
	}

});

/* PARTNERS */
$("body").on('click', '#addPartnerButton', function(event) {
	$.post("/showAddPartnerForm", {
		view : this.id
	}, function(data, status) {
		$("#main_content").html(data);
	});
});

$("body").on('click', '.partner_tbody .edit_icon', function(event) {
	event.stopPropagation();
	var id = $(this).parent().siblings(".unique_value").html()
	$.post("/showEditPartnerForm", {
		partnerID : id
	}, function(data, status) {
		var content = '<div id="main_content">' + data + '</div>'
		$("#main_content").html(data);
	});
});

$("body").on('click', '.partner_tbody .delete_icon', function(event) {
	event.stopPropagation();
	var userConfirm = confirm("Are you sure?");
	if (userConfirm === true) {
		var id = $(this).parent().siblings(".unique_value").html()

		$.post("/deletePartner", {
			partnerID : id
		}, function(data, status) {
			var content = '<div id="main_content">' + data + '</div>'
			$("#main_content").html(data);
		});
	}

});

$("body").on('click', '.partner_tbody .row_main_info', function(event) {
	event.stopPropagation();
	var id = $(this).children(".unique_value").html();
	var clickedRow = $(this);

	if ($(clickedRow).siblings(".partner_extra_info_row").length != 0) {
		$(".partner_extra_info_row").replaceWith("");
	} else {
		$.post("/getAdditionalPartnerInfo", {
			unique_value : id
		}, function(data, status) {
			$(clickedRow).after(data);
		});
	}

});

/* PROJECTS */
$("body").on('click', '#addProjectButton', function(event) {
	$.post("/showAddProjectForm", {
		view : this.id
	}, function(data, status) {
		$("#main_content").html(data);
	});
});
$("body").on('click', '.project_tbody .edit_icon', function(event) {
	event.stopPropagation();
	var id = $(this).parent().siblings(".unique_value").html()
	$.post("/showEditProjectForm", {
		projectID : id
	}, function(data, status) {
		var content = '<div id="main_content">' + data + '</div>'
		$("#main_content").html(data);
	});
});

$("body").on('click', '.project_tbody .delete_icon', function(event) {
	event.stopPropagation();

	var userConfirm = confirm("Are you sure?");
	if (userConfirm === true) {
		var id = $(this).parent().siblings(".unique_value").html()

		$.post("/deleteProject", {
			projectID : id
		}, function(data, status) {
			var content = '<div id="main_content">' + data + '</div>'
			$("#main_content").html(data);
		});
	}

});

$("body").on('click', '.project_tbody .row_main_info', function(event) {
	event.stopPropagation();
	var id = $(this).children(".unique_value").html();
	var clickedRow = $(this);

	if ($(clickedRow).siblings(".project_extra_info_row").length != 0) {
		$(".project_extra_info_row").replaceWith("");
	} else {
		$.post("/getAdditionalProjectInfo", {
			unique_value : id
		}, function(data, status) {
			$(clickedRow).after(data);
		});
	}

});

/* PARTNERSHIPS */
$("body").on('click', '#addPartnershipButton', function(event) {
	$.post("/showAddPartnershipForm", {
		view : this.id
	}, function(data, status) {
		$("#main_content").html(data);
	});
});

$("body").on('click', '.partnership_tbody .edit_icon', function(e) {
	event.stopPropagation();
	var id = $(this).parent().siblings(".unique_value").html()
	$.post("/showEditPartnershipForm", {
		projectID : id
	}, function(data, status) {
		var content = '<div id="main_content">' + data + '</div>'
		$("#main_content").html(data);
	});
});

$("body").on('click', '.partnership_tbody .delete_icon', function(event) {
	event.stopPropagation();

	userConfirm = confirm("Are you sure?");
	if (userConfirm === true) {
		var id = $(this).parent().siblings(".unique_value").html()

		$.post("/deleteProject", {
			projectID : id
		}, function(data, status) {
			var content = '<div id="main_content">' + data + '</div>'
			$("#main_content").html(data);
		});
	}

});

$("body").on('click', '.partnership_tbody .row_main_info', function(event) {
	event.stopPropagation();
	var id = $(this).children(".unique_value").html();
	var clickedRow = $(this);

	if ($(clickedRow).siblings(".project_extra_info_row").length != 0) {
		$(".project_extra_info_row").replaceWith("");
	} else {
		$.post("/getAdditionalProjectInfo", {
			unique_value : id
		}, function(data, status) {
			$(clickedRow).after(data);
		});
	}

});