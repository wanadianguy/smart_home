import React from 'react';
import ReactDOM from 'react-dom/client';
import "./app.css";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {Home} from "./pages/home";
import {Temperature} from "./pages/temperature";
import {Header} from "./components/header";
import {Movement} from "./pages/movement";
import {Brightness} from "./pages/brigthness";

const rootElement = document.getElementById('root') as HTMLElement;

const root = ReactDOM.createRoot(
  rootElement
);

rootElement.classList.add("global");

root.render(
    <>
        <Header title={window.location.href === `${window.origin}/` ? 'Home' : ''}/>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/temperature" element={<Temperature />}/>
                <Route path="/movement" element={<Movement />}/>
                <Route path="/brightness" element={<Brightness />}/>
            </Routes>
        </BrowserRouter>
    </>
);
