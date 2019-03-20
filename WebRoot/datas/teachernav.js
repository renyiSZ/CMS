var navs = [{
	"title": "Curriculum",
	"icon": "fa-book",
	"spread": true,
	"children": [{
		"title": "My Slides",
		"icon": "fa-navicon",
		"href": "teacher/mySlide.jsp"
	}, {
		"title": "My Online Courses",
		"icon": "fa-toggle-right",
		"href": "teacher/myVideo.jsp"
	}, {
		"title": "My Course Plan",
		"icon": "fa-calendar",
		"href": "PlanServlet?type=writeorigin"
	}, {
		"title": "My Students",
		"icon": "fa-address-book",
		"href": "CheckStudentServlet?show=first"
	
	}, {
		"title": "Student Notes",
		"icon": "fa-address-book",
		"href": "ShareServlet?type=teacherorigin"
	
	}]
}, {
	"title": "Homework",
	"icon": "fa-edit",
	"spread": false,
	"children": [{
		"title": "Upload Homework",
		"icon": "fa-upload",
		"href": "teacher/addHW.jsp"
	}, {
		"title": "Correct Homework",
		"icon": " fa-check ",
		"href": "TeacherHWServlet?type=correctorigin"
	}, {
		"title": "Assigned Homework ",
		"icon": " fa-send-o ",
		"href": "TeacherChartServlet?chart=assignedhworigin"
	},{
		"title": "Result and Analysis",
		"icon": "fa-edit ",
		"href": "TeacherChartServlet?chart=resultorigin"
	},{
		"title": "Group work",
		"icon": " fa-child",
		"href": "TeacherGroupWork?type=group"
	}]
}, {
	"title": "Grades",
	"icon": "fa-mortar-board ",
	"spread": false,
	"children": [{
		"title": "Release Grades",
		"icon": "fa-check-square-o",
		"href": "GradeAction?type=gradeorigin"
	}, {
		"title": "Grade Anaysis",
		"icon": "&#xe609;",
		"href": "GradeAction?type=listorigin"
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
}, {
	"title": "Feedback",
	"icon": "fa-comments-o",
	"href": "fa-mail-reply ",
	"spread": false,
	"children": [{
		"title": "From TA",
		"icon": "fa-mail-reply ",
		"href": "StudentAsk?type=TCollectOrigin&from=TA"
	}, {
		"title": "From Representative",
		"icon": "fa-mail-reply ",
		"href": "StudentAsk?type=TCollectOrigin&from=R"
	}, {
		"title": "From students",
		"icon": "fa-mail-reply ",
		"href": "StudentAsk?type=TCollectOrigin&from=student"
	
	}]
}];