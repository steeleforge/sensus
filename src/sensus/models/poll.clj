(ns sensus.views.index
  (use noir.core)
  (:require [sensus.models.poll :as polls]
            [sensus.models.question :as questions]
            [sensus.models.answer :as answers]
            [sensus.models.vote :as votes]
            [noir.session :as session]
            [sensus.db :as db])
)
  
(defn to-json [{:keys [title body user]}]
  {:title title
   :body body}
)

(defn create! [{:keys [title body]}]
  (let [uid ((session/get :user) :uid)]
    (when 
      (not (nil? uid))
        (db/insert! :polls
          {:title title
           :body body
           :user uid})
    )
  )
)

(defn find [{:keys pid}]
  (let [uid ((session/get :user) :uid)]
    (db/fetch-one
      :polls
      :where {:user uid}
    )
  )
)