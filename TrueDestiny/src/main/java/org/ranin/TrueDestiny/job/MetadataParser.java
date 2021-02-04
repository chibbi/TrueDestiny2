package org.ranin.TrueDestiny.job;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.ranin.TrueDestiny.App;

public class MetadataParser {

    MetadataValue plmad = new MetadataValue() {

        @Override
        public boolean asBoolean() {
            return true;
        }

        @Override
        public byte asByte() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public double asDouble() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public float asFloat() {
            return 1;
        }

        @Override
        public int asInt() {
            return 1;
        }

        @Override
        public long asLong() {
            return 1;
        }

        @Override
        public short asShort() {
            return 1;
        }

        @Override
        public String asString() {
            return "stuff";
        }

        @Override
        public Plugin getOwningPlugin() {
            return App.getPlugin(App.class);
        }

        @Override
        public void invalidate() {
            // TODO Auto-generated method stub

        }

        @Override
        public Object value() {
            return "stuff";
        }
    };
}
