// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBC__dOSHZnW9S4HqUkdDhr98ZX4PcysqQ",
  authDomain: "fable-studybuddy.firebaseapp.com",
  projectId: "fable-studybuddy",
  storageBucket: "fable-studybuddy.appspot.com",
  messagingSenderId: "702310538380",
  appId: "1:702310538380:web:70f57ae713bd8a6d046fa9",
  measurementId: "G-1M6N72T02P"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);