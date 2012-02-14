(ns sensus.db
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(def conn 
  (make-connection "mydb"
                   :host "127.0.0.1"
                   :port 27017))
(authenticate conn "myusername" "my password")
(set-connection! conn)