(ns advent-of-code-2022.day1
  (:require [advent-of-code-2022.core :as core]))

(defn parse-input [input]
  (let [input (core/load-input input)]
    (->> input
         (partition-by #(= "" %))
         (filter #(not= '("") %)))))

(defn sum-cals [cals]
  (reduce + (map #(Integer/parseInt %) cals)))

(defn part-1 [input]
  (let [cals-list (parse-input input)]
    (->> cals-list
         (map sum-cals)
         sort
         reverse
         first)))

(defn part-2 [input]
  (let [cals-list (parse-input input)]
    (->> cals-list
         (map sum-cals)
         sort
         reverse
         (take 3)
         (reduce +))))
