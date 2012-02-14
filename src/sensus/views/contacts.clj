(ns sensus.views.index
  (use noir.core
    hiccup.core
    hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

;; GET /usrs
;; hypermedia URI to each poll
(defpage [:get ["/user/:id" :id #"\d+"] {:keys [id]}] "This is a get")