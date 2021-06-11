import React, { Component } from 'react';
import Animal from './Animal';
import axios from 'axios';

class LostAnimals extends Component {
    constructor(props) {
        super(props)
        this.state = {
            composites: []
        }
    }

    componentDidMount() {
        const filter = {
            "daysAgo": 30,
            "loc": { "longitude": 42, "latitude": -88 },
            "mockAnimal": {},
            "radius": 40000
        };

        axios({
            method: 'POST',
            url: 'http://localhost:8080/api/composite/lost',
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
                        <h3>Lost Animals<hr /></h3>
                    </div>
                </div>
                <div className="row" id="lostAnimals">
                    {
                        this.state.composites.map(c =>
                            <Animal key={c.lat + c.lon + c.name + c.lastSeen} name={c.name} dateLost={c.lastSeen} lastSeen={c.lat + ", " + c.lon} dateFound="" id={c.animalId} breed={c.breed} species={c.species} color={c.color} height={c.height} weight={c.weight} gender={c.female} tag={c.nameTag} description={c.description} phone={c.phone} email={c.email} />)
                    }
                </div>
            </div>
        )
    }
}

export default LostAnimals