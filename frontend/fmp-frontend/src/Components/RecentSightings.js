import React, { Component } from 'react'
import Sighting from "./Sighting"

class RecentSightings extends Component {
    constructor(props) {
        super(props)
        this.state = {
            animalName: props.animalName,
            animalDateLost: props.animalDateLost,
            locationSighted: props.locationSighted
        }
    }

    render() {
        return (
            <div className="container rounded-right border border-dark bg-light vh-10">
                <div className="row">
                    <div className="col-12 mt-3">
                        <h3>Recent Sightings<hr /></h3>
                    </div>
                </div>
                <div className="row">
                    <div className="col-12 mt-2">
                        <Sighting animalName="Fido" location="test sighting location" date="mm/dd/yyyy" />
                        {/* <Sighting animalName="Fido" location="test sighting location" date="mm/dd/yyyy" /> */}
                    </div>
                    <div className="col-12 mt-2">
                        <Sighting animalName="Fido" location="test sighting location" date="mm/dd/yyyy" />
                    </div>
                    <div className="col-12 mt-2">
                        <Sighting animalName="Fido" location="test sighting location" date="mm/dd/yyyy" />
                    </div>
                </div>
            </div>
        )
    }
}

export default RecentSightings