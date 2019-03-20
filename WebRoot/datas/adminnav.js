var navs = [{
	"title": "User Information",
	"icon": "fa-user-circle",
	"spread": true,
	"children": [{
		"title": "Add User Info",
		"icon": "fa-plus",
		"href": "admin/addUser.jsp"
	}, {
		"title": "Edit User Info",
		"icon": "fa-wrench",
		"href": "admin/editUser.jsp"
	}]
}, {
	"title": "News",
	"icon": " fa-bars",
	"spread": false,
	"children": [{
		"title": "Release News",
		"icon": "fa-send-o ",
		"href": "admin/AddNews.jsp"
	}, {
		"title": "Edit News",
		"icon": "fa-wrench",
		"href": "admin/EditNews.jsp"
	}]
}, {
	"title": "Curriculum",
	"icon": " fa-book",
	"spread": false,
	"children": [{
		"title": "Add Curriculum",
		"icon": "fa-plus",
		"href": "admin/addClass.jsp"
	}, {
		"title": "Edit Curriculum",
		"icon": "fa-wrench",
		"href": "admin/editClass.jsp"
	}]
}, {
	"title": "Materials",
	"icon": "fa-files-o",
	"spread": false,
	"children": [{
		"title": "Edit Slides",
		"icon": "fa-wrench",
		"href": "AdminSlide?type=origin"
	}, {
		"title": "Edit Homework",
		"icon": "fa-wrench",
		"href": "AdminHW?type=origin"
	}, {
		"title": "Edit Videos",
		"icon": "fa-wrench",
		"href": "AdminVideo?type=origin"
	}]
}, {
	"title": "Forum",
	"icon": "fa-comments-o",
	"href": "",
	"spread": false,
	"children": [{
		"title": "Edit Post",
		"icon": "fa-wrench",
		"href": "admin/editPost.jsp"
	}, {
		"title": "Send a Post",
		"icon": "fa-envelope-o",
		"href": "addpost.jsp"
	
	}]
}];