import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import LostAnimals from "./Components/LostAnimals"

ReactDOM.render(
  <div className="container-fluid">
    <div className="row">
      <div className="col">
        <nav className="navbar navbar-expand-lg navbar-light bg-light border border-secondary rounded mt-3">
          <a className="navbar-brand">
            <h1>Find My Paws</h1>
          </a>
        </nav>
      </div>
    </div>
    <div className="row">
      <div className="col-7">
        <LostAnimals />
      </div>
    </div>
  </div>,
  document.getElementById('root')
)