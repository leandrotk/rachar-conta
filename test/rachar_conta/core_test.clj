(ns rachar-conta.core-test
  (:require [clojure.test :refer :all]
            [rachar-conta.core :refer :all]))

(deftest initialized-bill-test
  (testing "Test the value of the initialized bill"
    (is (= 0 @total-bill))
    (reset! total-bill 0)))

(deftest update-total-bill-test
  (testing "Test the total bill update"
    (is (= 0 @total-bill))
    (update-total-bill total-bill 10)
    (is (= 10 @total-bill))
    (update-total-bill total-bill 10)
    (is (= 20 @total-bill))
    (reset! total-bill 0)))

(deftest calculate-10%-tip-test
  (testing "Test the 10% tip calculation"
    (is (= 1.0 (calculate-10%-tip 10)))
    (is (= 10.0 (calculate-10%-tip 100)))
    (is (= 4.0 (calculate-10%-tip 40)))
    (is (= 5.5 (calculate-10%-tip 55)))
    (reset! total-bill 0)))

(deftest bill-for-each-test
  (testing "Test the bill for each"
    (is (= 10.0 (bill-for-each 100 10)))))

(deftest integration-test
  (testing "Simulates a `real` restaurant bill"
    (update-total-bill total-bill 22) ;; simple burger
    (update-total-bill total-bill 29) ;; x-bacon
    (update-total-bill total-bill 30) ;; veggie burger
    (update-total-bill total-bill 24) ;; x-burger
    (update-total-bill total-bill 5)  ;; coke
    (update-total-bill total-bill 7)  ;; heineken
    (update-total-bill total-bill 5)  ;; coke
    (update-total-bill total-bill 5)  ;; coke
    (update-total-bill total-bill (calculate-10%-tip @total-bill))
    (is (= 34.925 (bill-for-each @total-bill 4)))
    (reset! total-bill 0)))
