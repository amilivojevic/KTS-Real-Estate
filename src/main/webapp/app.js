/**
 * Created by Korisnik on 6/3/2017.
 */


(function(angular) {
    'use strict';
    angular
        .module('realEstateApp', [
            'ngResource',
            'ngRoute',
            'ngCookies',
            'restangular',
            'ui.router'
        ])
        .config(function($routeProvider,$stateProvider,$urlRouterProvider, $httpProvider){
            /*

            $routeProvider
                .when('/', {
                    templateUrl: 'app/static/notlogged_home.html'

                })
                .when('/login', {
                    templateUrl: 'app/auth/login/login.html',
                    controller: 'LoginController',
                    controllerAs: 'loginCtrl'
                })
                .when('/register', {
                    templateUrl: 'app/auth/register/register.html',
                    controller: 'RegisterController',
                    controllerAs: 'registerCtrl'
                })
                .when('/profile', {
                    templateUrl: 'app/user/owner/owner_profile.html',
                    controller: 'OwnerController',
                    controllerAs: 'ownerCtrl'
                })
                .when('/listings', {
                    templateUrl: 'app/static/listings.html',
                })
                .when('/about', {
                    templateUrl: 'app/static/about.html',
                })
                .when('/contact', {
                    templateUrl: 'app/static/contact.html',
                })
                .when('/modifyOwner', {
                    templateUrl: 'app/user/owner/owner_modify.html',
                    controller: 'OwnerModifyController',
                    controllerAs: 'ownerModifyCtrl'
                })
                .when('/addNewRealEstate', {
                    templateUrl: 'app/realEstate/addNewRealEstate.html',
                    controller: 'AddNewRealEstateController',
                    controllerAs: 'addNewRealEstateCtrl'
                })
                .otherwise({
                    redirectTo: '/'
                });
             */

            $urlRouterProvider.otherwise("/home");
            $stateProvider
                .state('notLoggedHome', {
                    url: '/home',
                    views: {
                        'content': {
                            templateUrl: 'app/static/notlogged_home.html',
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    },

                })
                .state('login', {
                    url: '/login',
                    views: {
                        'content': {
                            templateUrl: 'app/auth/login/login.html',
                            controller: 'LoginController',
                            controllerAs: 'loginCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('register', {
                    url: '/register',
                    views: {
                        'content': {
                            templateUrl: 'app/auth/register/register.html',
                            controller: 'RegisterController',
                            controllerAs: 'registerCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('profile', {
                    url: '/profile',
                    views: {
                        'content': {
                            templateUrl: 'app/user/owner/owner_profile.html',
                            controller: 'OwnerController',
                            controllerAs: 'ownerCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('ownerModify', {
                    url: '/owner_modify',
                    views: {
                        'content': {
                            templateUrl: 'app/user/owner/owner_modify.html',
                            controller: 'OwnerModifyController',
                            controllerAs: 'ownerModifyCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('admin', {
                    url: '/admin',
                    views: {
                        'content': {
                            templateUrl: 'app/user/admin/admin_profile.html',
                            controller: 'AdminController',
                            controllerAs: 'adminCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbarAdmin.html',
                            controller: 'NavbarAdminController',
                            controllerAs: 'navbarAdminCtrl'
                        }
                    }
                })
                .state('adminModify', {
                    url: '/admin_modify',
                    views: {
                        'content': {
                            templateUrl: 'app/user/admin/adminModify.html',
                            controller: 'AdminModifyController',
                            controllerAs: 'adminModifyCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbarAdmin.html',
                            controller: 'NavbarAdminController',
                            controllerAs: 'navbarAdminCtrl'
                        }
                    }
                })
                .state('addAdmin', {
                    url: '/add_admin',
                    views: {
                        'content': {
                            templateUrl: 'app/user/admin/addAdmin/addAdmin.html',
                            controller: 'AddAdminController',
                            controllerAs: 'addAdminCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbarAdmin.html',
                            controller: 'NavbarAdminController',
                            controllerAs: 'navbarAdminCtrl'
                        }
                    }
                })
                .state('addVerifier', {
                    url: '/add_verifier',
                    views: {
                        'content': {
                            templateUrl: 'app/user/admin/addVerifier/addVerifier.html',
                            controller: 'AddVerifierController',
                            controllerAs: 'addVerifierCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbarAdmin.html',
                            controller: 'NavbarAdminController',
                            controllerAs: 'navbarAdminCtrl'
                        }
                    }
                })
                .state('eraseOwner', {
                    url: '/erase_owner',
                    views: {
                        'content': {
                            templateUrl: 'app/user/admin/eraseOwner/eraseOwner.html',
                            controller: 'EraseOwnerController',
                            controllerAs: 'eraseOwnerCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbarAdmin.html',
                            controller: 'NavbarAdminController',
                            controllerAs: 'navbarAdminCtrl'
                        }
                    }
                })
                .state('verifyer', {
                    url: '/verifyer',
                    views: {
                        'content': {
                            templateUrl: 'app/user/verifyer/verifyerProfile.html',
                            controller: 'VerifyerController',
                            controllerAs: 'verifyerCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('verifyerModify', {
                    url: '/verifyer_modify',
                    views: {
                        'content': {
                            templateUrl: 'app/user/verifyer/verifyerModify.html',
                            controller: 'VerifyerModifyController',
                            controllerAs: 'verifyerModifyCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('listing', {
                    url: '/listing',
                    views: {
                        'content': {
                            templateUrl: 'app/static/listings.html'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('about', {
                    url: '/about',
                    views: {
                        'content': {
                            templateUrl: 'app/static/about.html',

                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('contact', {
                    url: '/contact',
                    views: {
                        'content': {
                            templateUrl: 'app/static/contact.html'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('addNewAdvertisement', {
                    url: '/add_new_advertisement',
                    views: {
                        'content': {
                            templateUrl: 'app/advertisement/addNewAdvertisement.html',
                            controller: 'AddNewAdvertisementController',
                            controllerAs: 'addNewAdvertisementCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('addNewRealEstate', {
                    url: '/add_new_real_estate',
                    views: {
                        'content': {
                            templateUrl: 'app/realEstate/addNewRealEstate.html',
                            controller: 'AddNewRealEstateController',
                            controllerAs: 'addNewRealEstateCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('getAllRealEstates', {
                    url: '/get_all_real_estates',
                    views: {
                        'content': {
                            templateUrl: 'app/realEstate/getAllRealEstates.html',
                            controller: 'GetAllRealEstatesController',
                            controllerAs: 'getAllRealEstatesCtrl'
                        },
                        'navbar': {
                            templateUrl: 'app/navbar/navbar.html',
                            controller: 'NavbarController',
                            controllerAs: 'navbarCtrl'
                        }
                    }
                })
                .state('verify', {
                    url: '/authenticate/{id:int}',
                    templateUrl: 'page/authentication.html',
                    controller: 'verificationController'
                });




            $httpProvider
                .interceptors.push(['$q', '$window',
                function($q, $window) {
                    return {
                        'request': function(config) {
                            config.headers = config.headers || {};
                            if($window.localStorage.token) {
                                config.headers["X-Auth-Token"] = $window.localStorage.token;
                            }
                            return config;
                        },
                        'responseError': function(response) {
                            if (response.status === 401 || response.status === 403) {

                                console.log("nesto kod interseptora je pogresno");
                            }
                            return $q.reject(response);
                        }
                    };
                }
            ]);

    })


})(angular);
