(ns clj-puppetdb.query-test
  (:require [clojure.test :refer :all]
            [clj-puppetdb.query :refer [canonicalize-query]]))

(deftest canonicalize-query-test
  (testing "The dreaded ~ operator"
    (is (= (canonicalize-query [:match :certname #"web\d+"])
           (canonicalize-query ["~" :certname #"web\d+"])
           ["~" :certname "web\\d+"])))
  (testing "Nested expressions"
    (is (= (canonicalize-query [:>= [:fact "uptime_days"] 10])
           [:>= [:fact "uptime_days"] 10])))
  (testing "Booleans don't get converted to strings"
    (is (= (canonicalize-query [:= [:fact "is_virtual"] false])
           [:= [:fact "is_virtual"] false]))))
