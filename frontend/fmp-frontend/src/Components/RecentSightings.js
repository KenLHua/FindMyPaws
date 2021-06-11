import React, { Component } from 'react';
import Sighting from "./Sighting";
import axios from "axios";

class RecentSightings extends Component {
    constructor(props) {
        super(props)
        this.state = {
            animalName: props.animalName,
            animalDateLost: props.animalDateLost,
            locationSighted: props.locationSighted,
            sComposites: []
        }
    }

    componentDidMount() {
        const filter = {
            "daysAgo": 30,
            "loc": { "longitude": 80, "latitude": -160 },
            "mockAnimal": {},
            "radius": 40000
        };

        axios({
            method: 'POST',
            url: 'http://localhost:8080/api/composite/all',
            data: filter
        })
            .then(res => {
                const sComposites = res.data;
                console.log(sComposites);
                this.setState({ sComposites });
            })
    }

    render() {
        return (
            <div className="container rounded-right border border-dark bg-light vh-10">
                <div className="row">
                    <div className="col-12 mt-3">
                        <h3>Recent Sightings<hr /></h3>
                    </div>
                </div>

                <div className="row" id="recentSightings">
                    {
                        this.state.sComposites.map(c =>
                            <Sighting key={c.lat + c.lon + c.name + c.lastSeen} animalName={c.name} location={c.lat + " " + c.lon} date={c.lastSeen} />
                        )
                    }
                </div>
            </div>
        )
    }
}

export default RecentSightings