import React, { Component } from 'react';
import Animal from './Animal';
import axios from 'axios';

class FoundAnimals extends Component {
    constructor(props) {
        super(props)
        this.state = {
            composites: []
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
            url: 'http://localhost:8080/api/composite/found',
            data: filter
        })
            .then(res => {
                const composites = res.data;
                console.log(composites);
                this.setState({ composites });
            })
    }

    render() {
        return (
            <div className="container ml-0 border border-dark rounded bg-light">
                <div className="row">
                    <div className="col-12 mt-3">
                        <h3>Found Animals<hr /></h3>
                    </div>
                </div>
                <div className="row" id="foundAnimals">
                    {
                        this.state.composites.map(c =>
                            <Animal key={c.lat + c.lon + c.name + c.lastSeen} name={c.name} dateFound={c.lastSeen} currentLocation={c.lat + " " + c.lon} />)
                    }
                </div>
            </div>
        )
    }
}

export default FoundAnimals