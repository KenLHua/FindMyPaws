import React, { Component } from 'react'
import LostAnimals from "./LostAnimals"
import FoundAnimals from "./FoundAnimals"
import Map from "./Map"
import RecentSightings from './RecentSightings'

class Home extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-6">
                        <LostAnimals />
                    </div>
                    <div className="col-6">
                        <FoundAnimals />
                    </div>
                </div>
                <div className="row">
                    <div className="col-6 mt-3 mb-3 mr-0 pr-0">
                        <Map />
                    </div>
                    <div className="col-6 mt-3 mb-3 ml-0 pl-0">
                        <RecentSightings />
                    </div>
                </div>
            </div>
        )
    }
}

export default Home