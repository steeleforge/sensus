(ns sensus.views.index
  (use noir.core
    hiccup.core
    hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))
  
;; get index
(defpage [:get "/"] [] "This is a get")
