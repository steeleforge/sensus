(ns sensus.views.index
  (use noir.core
    hiccup.core
    hiccup.page-helpers)
  (:require [sensus.models.poll :as polls]
            [sensus.models.question :as questions]
            [sensus.models.answer :as answers]
            [sensus.models.vote :as votes])
)
  

;; GET /polls
(defpage [:get "/polls"] {}
  (response/json 
    (map polls/to-json 
      (polls/find {})))
  (response/empty)))
  
  ;; POST /polls
(defpage [:post "/polls"] {} 
  (response/json 
    (polls/create! {}))
  (response/empty))

;; GET /polls/<pid>
;; poll title, body
;; hypermedia URI to questions
(defpage [:get ["/polls/:pid" :pid #"\d+"]] {:keys [pid]} 
  (response/json 
    (map polls/to-json
      (polls/find {:pid pid}))
  (response/empty))

;; GET /polls/<pid>/questions
;; list of questions & answers per question
(defpage [:get ["/polls/:pid/questions" :pid #"\d+"]] {:keys [pid]} 
  (response/json 
    (map questions/to-json
      (questions/list {:pid pid}))
  (response/empty))
  
  ;; POST /polls/<pid>/questions
(defpage [:get ["/polls/:pid/questions" :pid #"\d+"]] {:keys [pid]} 
  """
  Create a new question
  """
  (response/json 
    (questions/create! {:pid pid}) )
  (response/empty))  
  
;; GET /questions/<qid>
;; question title, body
;; list of answers
(defpage [:get ["/polls/:pid//questions/:qid" :pid #"\d+" :qid #"\d+"]] {:keys [pid, qid]} 
  (response/json 
    (map questions/to-json
      (questions/find {:pid pid :qid qid})))
  (response/empty))


;; GET /polls/<pid>/questions/<qid>/answers
;; list of answers for a question
(defpage [:get ["/polls/:pid/questions/:qid/answers" :pid #"\d+" :qid #"\d+"]] {:keys [pid, qid]} 
  (response/json 
    (map answers/to-json
      (answers/find {:pid pid :qid qid})))
  (response/empty))

;; POST /polls/<pid>/questions/<qid>/answers
;; create a new answer
(defpage [:get ["/polls/:pid/questions/:qid/answers" :pid #"\d+" :qid #"\d+"]] {:keys [pid, qid]} 
  (response/json 
    (answers/create! {:pid pid :qid qid}))
  (response/empty))

;; GET /answers/<aid>
;; single answer
(defpage [:get ["/polls/:pid/questions/:qid/answers/:aid" :pid #"\d+" :qid #"\d+":aid #"\d+"]] {:keys [pid, qid, aid]} 
  (response/json 
    (map answers/to-json
      (answers/find {:pid pid :qid qid :aid aid})))
  (response/empty))


;; POST /vote
;; POST expects pid, qid, aid, and cid
(defpage [:post ["/polls/:pid/questions/:qid/answers/:aid/vote"]] {:keys [pid qid aid cid]} 
  (response/json 
    (map votes/to-json
      (votes/create! {:cid cid :pid pid :qid qid :aid aid})))
  (response/empty))
