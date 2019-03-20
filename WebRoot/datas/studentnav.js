var navs = [{
	"title": "Curriculum",
	"icon": "fa-book",
	"spread": true,
	"children": [{
		"title": "My Slides",
		"icon": "fa-navicon",
		"href": "SlideServlet?action=allClassSlide"
	}, {
		"title": "My Online Courses",
		"icon": "fa-toggle-right",
		"href": "SlideServlet?action=allClassVideo"
	}, {
		"title": "My Course Plan",
		"icon": "fa-calendar",
		"href": "PlanServlet?type=readorigin"
	}, {
		"title": "My Teachers",
		"icon": "fa-address-book",
		"href": "student/teacher.jsp"
	
	},{
		"title": "Share Notes",
		"icon": "fa-exchange",
		"href": "ShareServlet?type=noteorigin"
	
	}]
}, {
	"title": "Homework",
	"icon": "fa-edit",
	"spread": false,
	"children": [{
		"title": "Hand in Homework",
		"icon": "fa-upload",
		"href": "StudentHomeworkServlet?require=handin&action=allClassHW"
	} ,{
		"title": "Submitted Homework",
		"icon": "fa-flag-o",
		"href": "StudentHomeworkServlet?require=submitted&action=allClassHW"
	} ,{
		"title": "Graded Homework",
		"icon": "fa-check",
		"href": "StudentHomeworkServlet?require=graded&action=allClassHW"
	} ,{
		"title": "Result",
		"icon": "fa-check-square-o",
		"href": "StudentHWResultServlet?action=allClassResult"
	},{
		"title": "Group work",
		"icon": " fa-child",
		"href": "StudentGroupShare?type=grouporigin"
	}]
}, {
	"title": "Grades",
	"icon": "fa-mortar-board ",
	"spread": false,
	"children": [{
		"title": "My Grades",
		"icon": "fa-check-square-o",
		"href": "StudentGrade?type=gradeorigin"
	}]
}, {
	"title": "Forum",
	"icon": "fa-comments-o",
	"href": "",
	"spread": false,
	"children": [{
		"title": "My Post",
		"icon": "fa-comment",
		"href": "student/mypost.jsp"
	}, {
		"title": "My Reply",
		"icon": "fa-comment-o",
		"href": "student/myreply.jsp"
	}, {
		"title": "Send a Post",
		"icon": "fa-envelope-o",
		"href": "addpost.jsp"
	
	}]
},{
	"title": "Advice or Questions",
	"icon": "fa-send-o ",
	"href": "",
	"spread": false,
	"children": [{
		"title": "To Representative",
		"icon": "fa-send-o ",
		"href": "StudentAsk?type=toRepresentative"
	},{
		"title": "Ask teachers",
		"icon": "fa-question",
		"href": "StudentAsk?type=toTeacher"
	},{
		"title": "Feedback",
		"icon": "fa-mail-reply ",
		"href": "StudentAsk?type=feedbackOrigin"
	}]
}];