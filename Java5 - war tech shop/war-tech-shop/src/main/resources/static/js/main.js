var app = angular.module("myapp",['ngRoute','ngCookies']);
// $routeProvider sử dụng điều hướng
app.config(function($routeProvider){
    $routeProvider
    .when('/',{
         templateUrl:'home.html',
        
    })
    .when('/subjects',{
        templateUrl:'subjects.html',
        controller:'subjectCtrl'
   })
   .when('/quiz/:id/:name',{
    templateUrl:'quiz.html',
    controller:'quizCtrl'
  })
  .when('/edit-profile', {
    templateUrl: 'edit-profile.html',
    controller:'edit-profile'
  })
  .when('/user-profile', {
    templateUrl: 'user-profile.html',
  })
  .otherwise({redirectTo: '/'});
})



// Bài kiểm tra quiz
app.directive('quizfpoly',function(quizFactory,$routeParams, $timeout,global,$http){
    return{
        restrict : 'AE',
        scope: {},
        templateUrl: 'template-quiz.html',
        link: function(scope, elem, attrs){
            scope.start = function(){
                quizFactory.getQuestions().then(function(){
                    scope.subjectName = $routeParams.name;
                 
                    scope.id = 0;
                    scope.quizOver = false;
                    scope.inProgess = true;
                    scope.getQuestion();
                    scope.startTime() ;
                })
               
            };

            

            scope.onTimeOut = function(){
              scope.counter--;
              myTimeOut = $timeout(scope.onTimeOut, 1000);
              if(scope.counter == -1){
                $timeout.cancel(myTimeOut);
                scope.quizOver = true;
                alert("Hết thời gian làm bài")
              }
            }
      
            scope.startTime = function(){
              scope.counter = 600;
              $timeout(scope.onTimeOut, 1000);
              
            }

            scope.reset = function(){
                scope.inProgess = false;
                scope.score = 0;
            };
            scope.getQuestion = function(){
                var quiz = quizFactory.getQuestion(scope.id);
                if(quiz){
                    scope.question = quiz.Text;
                    scope.options = quiz.Answers;
                    scope.answer = quiz.AnswerId;
                    scope.answerMode = true;
                }else{
                    scope.quizOver = true;
                }
              
             
            }
            // kiểm tra câu trả lời
            scope.checkAnswer = function(){
                // alert('answer');
                if(!$('input[name = answer]:checked').length) return;
                var ans = $('input[name = answer]:checked').val();
                if(ans == scope.answer){
                    // alert('Đúng');
                    scope.score ++;
                    scope.correctAns = true;
                }else{
                    // alert("sai");
                    scope.correctAns = false;
                }
                scope.answerMode = false;
            }
            scope.nextQuestion = function(){
                    scope.id ++;
                    scope.getQuestion();
                
            };
           

            scope.reset();

            
             // Lưu kết quả quiz
      scope.list_quizs = [];
      scope.listInforQuizs = [];
      $http.get('http://localhost:3000/quiz/'+global.getId()).then(function(res){
        scope.list_quizs = res.data;
        scope.listInforQuizs = scope.list_quizs.inforQuizs;
      })
      

        scope.saveQuiz = function(){
        if(scope.list_quizs.id != undefined){

          scope.ifQuiz = {
            namequiz: $routeParams.name,
            score: scope.score,
            date: new Date(),
          };

          scope.listInforQuizs.unshift(scope.ifQuiz);

          var dataUp = {
              inforQuizs: scope.listInforQuizs
          }
          $http.patch("http://localhost:3000/quiz/"+global.getId(),dataUp).then(function(res){
            alert("Lưu kết quả thành công");
          },function(error){
            alert("false");
          })
        }
        else{
          var data = {
            id: global.getId(),
            username: global.getUser(),
            inforQuizs: [
              {
                namequiz: $routeParams.name,
                score: scope.score,
                date: new Date()
              }
            ]

          }
          $http.post("http://localhost:3000/quiz",data).then(function(res){
              alert("Lưu kết quả thành công");
          },function(error){
              alert("false");
          })
      }
    }

        }
    }
});


// tạo service
app.factory('quizFactory',function($http, $routeParams){
   
    return{
        getQuestions: function(){
            return   $http.get('../db/Quizs/'+ $routeParams.id+'.js').then(function(res){
                questions = res.data;
            });
          },
          // laays 10 cau trong db
        getQuestion: function(id){
            var randomItem = questions[Math.floor(Math.random() * questions.length)];
            var count = questions.length;
            if(count > 10){
                count = 10;
            }
            
            if(id < count){
                return randomItem;
            }else{
                return false;
            }
        }
    }
});

app.controller("sliderCtrl",function($scope, $http){
    $scope.subjects= [];
    $http.get("../db/Subjects.js").then(function(response){
        $scope.subjects = response.data;
    });
    $(document).ready(function(){
        $(".list-subjects").slick({
            slidesToShow: 6,
            slidesToScroll: 3,
            autoplay: true,
            autoplaySpeed: 4000,
            prevArrow:"<button type='button' class='slick-prev pull-left'><i class='fa fa-angle-left' aria-hidden='true'></i></button>",
            nextArrow:"<button type='button' class='slick-next pull-right'><i class='fa fa-angle-right' aria-hidden='true'></i></button>"
        });
      });	
});


app.controller("subjectCtrl",function($scope, $http){
    $scope.subjects= [];
    $http.get("../db/Subjects.js").then(function(response){
        $scope.subjects = response.data;
    });
    $scope.begin=0;
    $scope.pageCount = $scope.subjects.length / 6;
    $scope.first= function(){
        $scope.begin= 0;
    };
    $scope.prev = function(){
        if($scope.begin > 0){
            $scope.begin -=6;
        }
      
    };
    $scope.next = function(){
        if($scope.begin  < (($scope.subjects.length / 6) - 1) *6){
            $scope.begin +=    6;
        }
    };

    $scope.last = function(){
        $scope.begin = ((($scope.subjects.length / 6)) - 1) * 6;
    };

    
 
});


// Câu hỏi
app.controller("quizCtrl",function($scope, $http, $routeParams , quizFactory){
        $http.get('../db/Quizs/'+ $routeParams.id+'.js').then(function(res){
            quizFactory.question = res.data;
        })
});

// Đăng kí
app.controller("registration", function($scope,$http){
  $scope.postdata = function(even){
    var data = {
          id: Math.random(),
          username: $scope.username,
          fullname: $scope.fullname,
          password: $scope.password,
          email: $scope.email,
          gender: $scope.gender,
          birthday: $scope.birthday
    }
    if($scope.registForm.$invalid){
      alert("Có thông tin chưa hợp lệ!!");
    }
    else{
      $http.post("http://localhost:3000/listStudent",data).then(function(res){
        alert("Đăng kí thành công");
  },function(error){
        alert("false");
  })
    };
  }


})

// Dang nhap
app.controller("studentController", function($scope, $http,$window, $location,$rootScope,global,$window){
  $rootScope.success = false;
  $scope.student= {};
  $scope.list_students = [];
  $http.get('http://localhost:3000/listStudent').then(function(res){
      $scope.list_students = res.data;
  });
  $scope.login = function(){
    var username = $scope.student.username;
    var password = $scope.student.password;
    $scope.closeDia = true;
    // alert(username[$scope.list_students[1].username]);
    for(var i = 0 ; i < $scope.list_students.length; i++){
      if((username == $scope.list_students[i].username) && (password == $scope.list_students[i].password) ){
        global.setUser($scope.list_students[i].username,i,$scope.list_students[i].id);
        $rootScope.index = i;
        alert("Đăng nhập thành công");
        document.getElementById("close-login").click();
        // $window.location.href = "http://127.0.0.1:5501/index.html#!/";
        $location.path('/');
        $scope.closeDia = true;
        return ;
      }
    }
    if($scope.myForm.$valid){
      alert("Sai tên đăng nhập hoặc mật khẩu");
      $scope.closeDia = false;
      
    }
  }
  $scope.logout = function(response){
    $scope.isLogout();
    $location.path('/');
  }

  $scope.isLogin = function(){
    $scope.userInLogin = global.getUser('user');
    if($scope.userInLogin != null){
        return true;
    }
    else{
      return false;
    }
  }

 

  $scope.isLogout = function(){
    $scope.userInLogin = global.getUser('user');
    console.log($scope.userInLogin);
    if($scope.userInLogin != null){
      global.setUser();
    }
    // $scope.userInLogin = $scope.closeDia

}
   // Lấy lại mật khẩu
  $scope.forgotpassword = function(){
    var username = $scope.student.username;
    var email = $scope.student.email;
    for (var i = 0; i < $scope.list_students.length; i++) {
      if(username ==  $scope.list_students[i].username && email ==  $scope.list_students[i].email){
        // alert('Mật khẩu cũ của bạn là:'+$scope.list_students[i].password);
        if(confirm('Mật khẩu cũ của bạn là:'+$scope.list_students[i].password)){
          $window.location.href = 'http://127.0.0.1:5501/index.html#!/';
          document.getElementById("close-forgot").click();
        }
        return;
      }
    }
    alert("Sai tên đăng nhập hoặc email");
  }

  angular.element(document).ready(function(){
    var scrolltoOffset = $('#header').outerHeight() - 1;
  $(document).on('click', '.nav-menu a, .mobile-nav a, .scrollto', function(e) {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      if (target.length) {
        e.preventDefault();

        var scrollto = target.offset().top - scrolltoOffset;

        if ($(this).attr("href") == '#header') {
          scrollto = 0;
        }

        $('html, body').animate({
          scrollTop: scrollto
        }, 1500, 'easeInOutExpo');

        if ($(this).parents('.nav-menu, .mobile-nav').length) {
          $('.nav-menu .active, .mobile-nav .active').removeClass('active');
          $(this).closest('li').addClass('active');
        }

        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
          $('.mobile-nav-overly').fadeOut();
        }
        return false;
      }
    }
  });

  // Activate smooth scroll on page load with hash links in the url
  $(document).ready(function() {
    if (window.location.hash) {
      var initial_nav = window.location.hash;
      if ($(initial_nav).length) {
        var scrollto = $(initial_nav).offset().top - scrolltoOffset;
        $('html, body').animate({
          scrollTop: scrollto
        }, 1500, 'easeInOutExpo');
      }
    }
  });

  // Mobile Navigation
  if ($('.nav-menu').length) {
    var $mobile_nav = $('.nav-menu').clone().prop({
      class: 'mobile-nav d-lg-none'
    });
    $('body').append($mobile_nav);
    $('body').prepend('<button type="button" class="mobile-nav-toggle d-lg-none"><i class="icofont-navigation-menu"></i></button>');
    $('body').append('<div class="mobile-nav-overly"></div>');

    $(document).on('click', '.mobile-nav-toggle', function(e) {
      $('body').toggleClass('mobile-nav-active');
      $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
      $('.mobile-nav-overly').toggle();
    });

    $(document).on('click', '.mobile-nav .drop-down > a', function(e) {
      e.preventDefault();
      $(this).next().slideToggle(300);
      $(this).parent().toggleClass('active');
    });

    $(document).click(function(e) {
      var container = $(".mobile-nav, .mobile-nav-toggle");
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
          $('.mobile-nav-overly').fadeOut();
        }
      }
    });
  } else if ($(".mobile-nav, .mobile-nav-toggle").length) {
    $(".mobile-nav, .mobile-nav-toggle").hide();
  }

  // Navigation active state on scroll
  var nav_sections = $('section');
  var main_nav = $('.nav-menu, .mobile-nav');

  $(window).on('scroll', function() {
    var cur_pos = $(this).scrollTop() + 200;

    nav_sections.each(function() {
      var top = $(this).offset().top,
        bottom = top + $(this).outerHeight();

      if (cur_pos >= top && cur_pos <= bottom) {
        if (cur_pos <= bottom) {
          main_nav.find('li').removeClass('active');
        }
        main_nav.find('a[href="#' + $(this).attr('id') + '"]').parent('li').addClass('active');
      }
      if (cur_pos < 300) {
        $(".nav-menu ul:first li:first").addClass('active');
      }
    });
  });

  if ($(window).scrollTop() > 100) {
    $('#header').addClass('header-scrolled');
  }

  // Back to top button
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top').fadeIn('slow');
    } else {
      $('.back-to-top').fadeOut('slow');
    }
  });

  $('.back-to-top').click(function() {
    $('html, body').animate({
      scrollTop: 0
    }, 1500, 'easeInOutExpo');
    return false;
  });

  // Skills section
  $('.skills-content').waypoint(function() {
    $('.progress .progress-bar').each(function() {
      $(this).css("width", $(this).attr("aria-valuenow") + '%');
    });
  }, {
    offset: '80%'
  });

  // jQuery counterUp
  $('[data-toggle="counter-up"]').counterUp({
    delay: 10,
    time: 1000
  });

  // Porfolio isotope and filter - Load dữ liệu theo filter
  $(window).on('load', function() {
    var portfolioIsotope = $('.portfolio-container').isotope({
      itemSelector: '.portfolio-item',
      layoutMode: 'fitRows'
    });

    $('#portfolio-flters li').on('click', function() {
      $("#portfolio-flters li").removeClass('filter-active');
      $(this).addClass('filter-active');

      portfolioIsotope.isotope({
        filter: $(this).data('filter')
      });
    });

    // Initiate venobox (lightbox feature used in portofilo)
    $(document).ready(function() {
      $('.venobox').venobox();
    });
  });

  // Testimonials carousel (uses the Owl Carousel library)
  $(".testimonials-carousel").owlCarousel({
    autoplay: true,
    dots: true,
    loop: true,
    responsive: {
      0: {
        items: 1
      },
      768: {
        items: 2
      },
      900: {
        items: 3
      }
    }
  });

  // Portfolio details carousel
  $(".portfolio-details-carousel").owlCarousel({
    autoplay: true,
    dots: true,
    loop: true,
    items: 1
  });
  });  
})

//Đồng hồ
app.filter('Timer', function($filter){
  return function(number){
      var minutes = Math.round((number - 30) / 60);
      var remainingSeconds = number % 60;
      if(remainingSeconds < 10){
          remainingSeconds = "0" + remainingSeconds;
      }
      var timer = (minutes = (minutes < 10) ? "0" + minutes : minutes) + ":" + remainingSeconds;
      return timer;
  }
});


//Set Cookies after user login
app.service('global', function($cookies, $location, $filter) {
  var expireDate = new Date();
  expireDate.setTime(expireDate.getTime() + 1 * 3600 * 1000);
  var globalService = {};
  globalService.user = $cookies.getObject('user');
  globalService.index = null;
  globalService.isAuth = function (){
      if (globalService.user == null) {
          globalService.user = $cookies.getObject('user');
      }
      return (globalService.user != null);
  };
  globalService.setUser = function(newUser,index,id) {
      globalService.user = newUser;
      globalService.index = index;
      globalService.Id = id;
      if (globalService.user == null){
        $cookies.remove('user');
        $cookies.remove('index');
        $cookies.remove('id');
      } 
      else{
        $cookies.putObject('user', globalService.user, {'expires': expireDate});
        $cookies.putObject('index', globalService.index, {'expires': expireDate});
        $cookies.putObject('id', globalService.Id, {'expires': expireDate});
      } 
  };
  globalService.getUser = function() {
      return $cookies.getObject('user');
  };
  globalService.getindex = function() {
    return $cookies.getObject('index');
};
  globalService.getId = function() {
    return $cookies.getObject('id');
};
  return globalService;
});



// Đổi mật khẩu
app.controller("edit-pass", function($scope,$http,$location,$route,global){
  $scope.list_students = [];
  $http.get('http://localhost:3000/listStudent').then(function(res){
      $scope.list_students = res.data;
  })
  $scope.editpass = function(even){
    var oldpass = $scope.oldpass;
          if(oldpass == $scope.list_students[global.getindex()].password){
            var data = {
              password:  $scope.newpass,
            }
            $http.patch(("http://localhost:3000/listStudent/"+global.getId()),data).then(function(res){
              console.log("true");
            },function(error){
              alert("false");
            })
            alert("Đổi mật khẩu thành công");
          document.getElementById("close-editpass").click();
            
          }
          else{
            alert('Mật khẩu hiện tại không đúng');
          }
  }
})

// Đổi thông tin cá nhân
app.controller("edit-profile", function($scope,$http,$location,$route,global){
  
  $scope.editprofile = function(even){
            var data = {
              fullname: $scope.fullname,
              email:  $scope.email,
              gender: $scope.gender,
              birthday: $scope.birthday
            }
            $http.patch(("http://localhost:3000/listStudent/"+global.getId()),data).then(function(res){
              alert("Thay đổi thông tin thành công")
            },function(error){
              alert("Lỗi");
            })
  }

  // Thông tin cá nhân
  $scope.infors = {};
  $http.get(("http://localhost:3000/listStudent/"+global.getId())).then(function(res){
      $scope.infors = res.data;
      console.log($scope.infors);
  })

  // Thông tin quiz đã lưu
  $scope.quizs = {};
  $http.get(("http://localhost:3000/quiz/"+global.getId())).then(function(res){
      $scope.quizs = res.data;
      console.log($scope.quizs);
  })

  // Tabs

  $scope.tab = 1;

  $scope.setTab = function(newTab){
    $scope.tab = newTab;
  }

  $scope.isSet = function(tabNum){
    return $scope.tab == tabNum;
  }
})
